package com.book.book_project;

import java.io.IOException;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.repository.MemberRepository;
import com.book.book_project.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler  {

	//private final MemberService service;
	private final MemberRepository memberRepository;
	
	
	//로그인 성공 시 해야 할 명령문
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// authentication.getName(); --> 로그인 시 입력된 userid 값을 가져옴
	//	MemberDTO member = service.memberInfo(authentication.getName());
		MemberEntity memberInfo = memberRepository.findById(authentication.getName()).get();
		//마지막 로그인 날짜 등록
//		member.setLastlogindate(LocalDateTime.now());
//		service.lastlogindateUpdate(member);	
		
		//패스워드 확인 후 마지막 패스워드 변경일이 30일이 경과 되었을 경우 ...
		
		
		//세션 생성
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(3600*24*7);//세션 유지 기간 설정
		session.setAttribute("userid", memberInfo.getUserid());
		session.setAttribute("username", memberInfo.getUsername());
		session.setAttribute("role", memberInfo.getRole());
		session.setAttribute("FromSocial", memberInfo.getFromSocial());
		
	//	log.info("************************* FormLogin 성공 *************************");
		
		
		setDefaultTargetUrl("/product/main");
		super.onAuthenticationSuccess(request, response, authentication);
		
	}
	
	
	
	
}
