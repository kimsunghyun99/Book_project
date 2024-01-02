package com.book.book_project.entity.repository;

import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.RefundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefundRepository extends JpaRepository<RefundEntity,Integer> {

    @Query(value="delete from tbl_refund where purchaseinfonumber=:purchaseinfonumber", nativeQuery = true)
    void deletepurchase(@Param("purchaseinfonumber") int purchaseinfonumber);

}
