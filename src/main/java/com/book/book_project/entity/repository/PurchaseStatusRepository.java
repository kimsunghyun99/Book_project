package com.book.book_project.entity.repository;

import com.book.book_project.entity.PurchaseInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseStatusRepository extends JpaRepository<PurchaseInfoEntity,Integer> {
}
