package com.book.book_project.entity.repository;

import com.book.book_project.entity.DeliveryAddrEntity;
import com.book.book_project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryAddrEntity, Integer> {
}
