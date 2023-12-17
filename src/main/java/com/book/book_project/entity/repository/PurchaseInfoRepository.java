package com.book.book_project.entity.repository;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfoEntity, Integer> {

    List<PurchaseInfoEntity> findByBuyerseq(int buyerseq);

}
