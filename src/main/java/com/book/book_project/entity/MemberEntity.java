package com.book.book_project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity(name="member")
@Table(name="tbl_member")
public class MemberEntity {

    @Id
    @Column(name="userid", nullable=false)
    private String userid;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="nickname", length=20, nullable=false)
    private String nickname;

    @Column(name="zipcode", length=50, nullable=true)
    private String zipcode;

    @Column(name="address", length=1000, nullable=true)
    private String address;

    @Column(name="username", length=200, nullable=false)
    private String username;

    @Column(name="birthday", length=2, nullable = false)
    private LocalDateTime birthday;

    @Column(name="gender", length=20, nullable=true)
    private String gender;

    @Column(name="telno", length=20, nullable=true)
    private String telno;

    @Column(name="password", length=200, nullable=false)
    private String password;


    @Column(name="pwcheck",  nullable=true)
    private int pwcheck;


    @Column(name="regdate", nullable=false)
    private LocalDateTime regdate;

    @Column(name="point", length=2, nullable = true)
    private int point;

    @Column(name="memberclass", length=2, nullable = false)
    private String memberclass;

    @Column(name="likauthor", length=2, nullable = false)
    private String likeauthor;

    @Column(name="likegenre", length=2, nullable = false)
    private String likegenre;


    @Column(name="lastlogindate",  nullable=true)
    private LocalDateTime lastlogindate;

    @Column(name="lastlogoutdate",  nullable=true)
    private LocalDateTime lastlogoutdate;

    @Column(name="lastpwdate", nullable=true)
    private LocalDateTime lastpwdate;

    @Column(name="fromsocial", length=2, nullable = false)
    private String fromSocial;

    @Column(name="role", length=50, nullable=false)
    private String role;



}
