package com.book.book_project.entity.repository;

import com.book.book_project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

    // 아이디찾기 select userid from tbl_member where username = #{username} and telno = #{telno}
    public Optional<MemberEntity> findByUsernameAndTelno(String username, String telno);
}
