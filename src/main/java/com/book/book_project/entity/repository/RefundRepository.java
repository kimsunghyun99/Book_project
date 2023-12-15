package com.book.book_project.entity.repository;

import com.book.book_project.entity.RefundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<RefundEntity,Integer> {
}
