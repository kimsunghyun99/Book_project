package com.book.book_project.entity.repository;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.PurchaseStatusEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Transactional
@Repository
public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfoEntity, Integer> {

    List<PurchaseInfoEntity> findByBuyerseq(BuyerInfoEntity buyerseq);
    //주문 상태 불러오기
    @Query(value = "select * from tbl_purchasestatus", nativeQuery = true)
    List<Map<String,String>> statuslist();

    //회원 주문 관리
    @Query(value = "SELECT p.* ,pr.*, b.* , s.*\n" +
            "FROM tbl_purchaseinfo p\n" +
            "JOIN tbl_buyerinfo b ON p.buyerseq = b.buyerseq\n" +
            "JOIN tbl_product pr ON p.bookid = pr.bookid\n" +
            "JOIN tbl_purchasestatus s ON p.statusseq = s.statusseq;", nativeQuery = true)
    List<Map<String, String>> mempurchaseinfo();

    //비회원 주문 관리
    @Query(value = "SELECT u.*, p.* , um.*, s.*\n" +
            "FROM tbl_unmemberpurchaseinfo u\n" +
            "JOIN tbl_unmember um ON u.unmemberseq = um.unmemberseq\n" +
            "JOIN tbl_product p ON u.bookid = p.bookid\n" +
            "JOIN tbl_purchasestatus s on u.statusseq = s.statusseq", nativeQuery = true)
    List<Map<String, String>> unpurchaseinfo();

    //회원 주문 상태 변경
    @Transactional
    @Modifying//테이블 DML(update, insert, delete)을 실행 시켜 변화를 주었을 경우 테이블에 반영된 내용을 엔티티 클래스에 반영
    @Query(value = "update tbl_purchaseinfo set statusseq = :statusseq where purchaseinfonumber = :purchaseinfonumber", nativeQuery = true)
    public void memberorderupdate(@Param("statusseq") int statusseq, @Param("purchaseinfonumber") int purchaseinfonumber);

    //비회원 주문 상태 변경
    //회원 주문 상태 변경
    @Transactional
    @Modifying//테이블 DML(update, insert, delete)을 실행 시켜 변화를 주었을 경우 테이블에 반영된 내용을 엔티티 클래스에 반영
    @Query(value = "update tbl_unmemberpurchaseinfo set statusseq = :statusseq where unmember_purseq =:unmember_purseq", nativeQuery = true)
    public void unmemberorderupdate(@Param("statusseq") int statusseq, @Param("unmember_purseq") int unmember_purseq);

}
