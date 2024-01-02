package com.book.book_project.entity.repository;

import com.book.book_project.entity.PurchaseInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseStatusRepository extends JpaRepository<PurchaseInfoEntity,Integer> {


@Query(value="select statusname from tbl_purchasestatus where statusseq = :statusseq", nativeQuery = true)
        public String getStatusName(@Param("statusseq") int statusseq);




}
