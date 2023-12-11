package com.book.book_project.entity.repository;

import com.book.book_project.entity.BuyerInfoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PurchaseInfoRepository extends JpaRepository<BuyerInfoEntity, String> {


}
