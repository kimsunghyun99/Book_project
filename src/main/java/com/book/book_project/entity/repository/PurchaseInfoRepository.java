package com.book.book_project.entity.repository;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.PurchaseStatusEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfoEntity, Integer> {

//    List<PurchaseInfoEntity> findByBuyerseq(BuyerInfoEntity buyerseq);
PurchaseInfoEntity findByBuyerseq( BuyerInfoEntity buyerseq);


    @Transactional
    @Modifying //  테이블에 DML ( insert, update, delete) 을 실행 시켜 변화를 주었을 경우 테이블에 반영된 내용을 엔티티 클래스에 반영
    @Query(value="update tbl_purchaseinfo set statusseq = :statusseq where purchaseinfonumber = :purchaseinfonumber",nativeQuery = true)
    public void updateStatusseq(@Param("statusseq") int statusseq, @Param("purchaseinfonumber") int purchaseinfonumber);
}
