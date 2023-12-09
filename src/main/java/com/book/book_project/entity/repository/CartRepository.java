package com.book.book_project.entity.repository;

import com.book.book_project.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    //장바구니 추가

    //장바구니 삭제

}
