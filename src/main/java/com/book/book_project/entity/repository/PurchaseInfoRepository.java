package com.book.book_project.entity.repository;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Transactional
@Repository
public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfoEntity, Integer> {

    List<PurchaseInfoEntity> findByBuyerseq(BuyerInfoEntity buyerseq);

    //회원 주문 관리
    @Query(value = "SELECT pr.*, b.*\n" +
            "FROM tbl_purchaseinfo p\n" +
            "JOIN tbl_buyerinfo b ON p.buyerseq = b.buyerseq\n" +
            "JOIN tbl_product pr ON p.bookid = pr.bookid\n" +
            "WHERE p.statusseq = 1;", nativeQuery = true)
    List<Map<String, String>> mempurchaseinfo();

    //비회원 주문 관리
    @Query(value = "SELECT p.* , um.*\n" +
            "FROM tbl_unmemberpurchaseinfo u\n" +
            "JOIN tbl_unmember um ON u.unmemberseq = um.unmemberseq\n" +
            "JOIN tbl_product p ON u.bookid = p.bookid\n" +
            "WHERE u.statusseq = 1", nativeQuery = true)
    List<Map<String, String>> unpurchaseinfo();


}
