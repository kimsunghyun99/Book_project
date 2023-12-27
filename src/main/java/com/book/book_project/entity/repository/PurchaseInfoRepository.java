package com.book.book_project.entity.repository;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfoEntity, Integer> {

    List<PurchaseInfoEntity> findByBuyerseq(BuyerInfoEntity buyerseq);

    //회원 / 비회원 주문 관리
    @Query(value = "SELECT p.* \n" +
            "FROM tbl_product p \n" +
            "JOIN tbl_purchaseinfo i ON p.bookid = i.bookid \n" +
            "WHERE i.statusseq = 1\n" +
            "\n" +
            "UNION\n" +
            "\n" +
            "SELECT p.* \n" +
            "FROM tbl_product p \n" +
            "JOIN tbl_unmemberpurchaseinfo u ON p.bookid = u.bookid\n" +
            "WHERE u.statusseq = 1; ", nativeQuery = true)
    List<ProductEntity> purchaselist();


}
