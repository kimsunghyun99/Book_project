package com.book.book_project.entity;

import java.sql.Timestamp;
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

    @Column(name="nickname", length=100, nullable= true)
    private String nickname;

    @Column(name="username", length=200, nullable=false)
    private String username;

    @Column(name="birthday", nullable = true)
    private String birthday;

    @Column(name="gender", length=10, nullable=true)
    private String gender;

    @Column(name="telno", length=15, nullable=true)
    private String telno;

    @Column(name="password", length=200, nullable=false)
    private String password;

    @Column(name="regdate", nullable=true)
    private Timestamp regdate;

    @Column(name="point", length=50, nullable = true)
    private int point;

    @Column(name="memberclass", length=50, nullable = true)
    private String memberclass;

    @Column(name="pwcheck",  nullable=true)
    private int pwcheck;

    @Column(name="lastlogindate")
    private Timestamp lastlogindate;

    @Column(name="lastlogoutdate")
    private Timestamp lastlogoutdate;

    @Column(name="lastpwdate")
    private Timestamp lastpwdate;

    @Column(name="fromsocial", length=2, nullable = false)
    private String fromSocial;

    @Column(name="role", length=50, nullable=false)
    private String role;

}
