package com.book.book_project.entity.repository;

import com.book.book_project.entity.MemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.Optional;

@Transactional
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {


    @Query(value="select userid from tbl_member where telno=? and username=? and birthday=? or email=?", nativeQuery = true)
    Optional<MemberEntity> findByTelnoOrEmail(@Param("telno") String telno, @Param("username") String username, @Param("birthday") int birthday, @Param("email") String email);
}
