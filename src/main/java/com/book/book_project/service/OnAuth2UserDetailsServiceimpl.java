package com.book.book_project.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.book.book_project.dto.MemberOAuth2DTO;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class OnAuth2UserDetailsServiceimpl extends DefaultOAuth2UserService {
	
	private final PasswordEncoder pwdEncoder;
	private final MemberRepository memberRepository;
	private final HttpSession session;
	
	@Override
		public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
			
		OAuth2User oAuth2User = super.loadUser(userRequest);

		//23.12.13 수정
		String provider  = userRequest.getClientRegistration().getRegistrationId();
		String providerId = "";
		String email = "";

		//23.12.13 수정
		if (provider.equals("naver")) {  // 네이버 로그인인 경우
			Map<String, Object> attributes = oAuth2User.getAttributes();
			Map<String, Object> response = (Map<String, Object>) attributes.get("response");
			providerId = response.get("id").toString();
			email = (String) response.get("email");
		} else {  // 구글 로그인인 경우
			providerId = oAuth2User.getAttribute("sub");
			email = oAuth2User.getAttribute("email");
		}
		
		log.info("provider = {}", provider);
		log.info("providerId = {}", providerId);
		log.info("email = {}", email);
		
		oAuth2User.getAttributes().forEach((k,v ) ->  {
			log.info(k + ":" + v);
		});
		
		MemberEntity member = saveSocialMember(email);
		
		// Role 값 읽어들임
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getRole()); 
		grantedAuthorities.add(grantedAuthority);

		MemberOAuth2DTO memberOAuth2DTO =new MemberOAuth2DTO();
		// attributes, authorities, name을 MemberOAuth2DTO에 넣어 줌.
		memberOAuth2DTO.setAttribute(oAuth2User.getAttributes());
		memberOAuth2DTO.setAuthorities(grantedAuthorities);
		memberOAuth2DTO.setName(member.getUsername());
		
		session.setAttribute("email", email);
		session.setAttribute("username", member.getUsername());
		session.setAttribute("role", member.getRole());
		//session.setAttribute("FromSocial", member.getFromSocial());
		session.setAttribute("FromSocial","Y");
		
			return memberOAuth2DTO;
		
		}

	//23.12.13 수정
	private MemberEntity saveSocialMember(String email) {
	    
		// 구글 회원 계정으로 로그인 한 회원의 경우  사이트 운영에 필요한 최소한의 정보를 
		// 가공해서 tbl_member에 입력해야 함
		
		
		Optional<MemberEntity> result = memberRepository.findById(email);
		if(result.isPresent()) { 
			return result.get();
		}

		String provider = email.split("@")[1];  // 이메일 주소에서 제공자를 추출
		String username = provider.equals("naver.com") ? "네이버회원" : "구글회원";

		MemberEntity member = MemberEntity.builder()
				.userid(email)
				.username(username)
				.password(pwdEncoder.encode("12345"))
				.regdate(Timestamp.valueOf(LocalDateTime.now()))
				.fromSocial("Y")
				.role("USER")
				.build();
		
		memberRepository.save(member);

		return member;
	
}
}
