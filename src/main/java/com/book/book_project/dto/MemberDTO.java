package com.book.book_project.dto;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.book.book_project.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MemberDTO {
    private String userid;
    private String email;
    private String nickname;
    private String zipcode;
    private String username;
    private String address;
    private LocalDateTime birthday; // 생일
    private String gender;
    private String telno;
    private String password;
    private int pwcheck;   // 초기값 1
    private LocalDateTime regdate;      // 초기값 LocalDateTime.now()
    private int point;    // 마일리지
    private String memberclass; // 회원등급
    private String likeauthor;     // 좋아하는 작가
    private String likegenre;     // 좋아하는 장르
    private LocalDateTime lastlogindate;
    private LocalDateTime lastlogoutdate;
    private LocalDateTime lastpwdate;  // 초기값 regdate와 동일
    private String fromSocial;    // 회원등록 시 초기값 N
    private String role;     // 초기값 USER


    // Entity -> DTO로 이동


    public MemberDTO(MemberEntity memberEntity) {
        this.userid = memberEntity.getUserid();
        this.email = memberEntity.getEmail();
        this.nickname = memberEntity.getNickname();
        this.zipcode = memberEntity.getZipcode();
        this.address = memberEntity.getAddress();
        this.username = memberEntity.getUsername();
        this.birthday = memberEntity.getBirthday();
        this.gender = memberEntity.getGender();
        this.telno = memberEntity.getTelno();
        this.password = memberEntity.getPassword();
        this.pwcheck = memberEntity.getPwcheck();
        this.regdate = memberEntity.getRegdate();
        this.point = memberEntity.getPoint();
        this.memberclass = memberEntity.getMemberclass();
        this.likeauthor = memberEntity.getLikeauthor();
        this.likegenre = memberEntity.getLikegenre();
        this.lastlogindate = memberEntity.getLastlogindate();
        this.lastlogoutdate = memberEntity.getLastlogoutdate();
        this.lastpwdate = memberEntity.getLastpwdate();
        this.fromSocial = memberEntity.getFromSocial();
        this.role = memberEntity.getRole();



    }


    // DTO -> Entity로 이동 (Builder 패턴 사용)
    public MemberEntity dtoToEntity(MemberDTO member) {
        MemberEntity memberEntity = MemberEntity.builder()
                .userid(member.getUserid())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .zipcode(member.getZipcode())
                .address(member.getAddress())
                .username(member.getUsername())
                .birthday(member.getBirthday())
                .gender(member.getGender())
                .telno(member.getTelno())
                .password(member.getPassword())
                .pwcheck(member.getPwcheck())
                .regdate(member.getRegdate())
                .point(member.getPoint())
                .memberclass(member.getMemberclass())
                .likeauthor(member.getLikeauthor())
                .likegenre(member.getLikegenre())
                .lastlogindate(member.getLastlogindate())
                .lastlogoutdate(member.getLastlogoutdate())
                .lastpwdate(member.getLastpwdate())
                .fromSocial(member.getFromSocial())
                .role(member.getRole())

                .build();


        return memberEntity;
    }


}
