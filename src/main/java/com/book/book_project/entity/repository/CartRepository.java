package com.book.book_project.entity.repository;

import com.book.book_project.dto.CartDTO;
import com.book.book_project.entity.CartEntity;
import com.book.book_project.entity.CartEntityID;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, CartEntityID> {

    @Query(value = "select userid, bookid, cartregdate from tbl_cart where userid =:userid", nativeQuery = true)
    List<CartEntity> findByUserid(@Param("userid") String userid);
}
