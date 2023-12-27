package com.book.book_project.entity.repository;

import com.book.book_project.dto.ProductDTO;
import com.book.book_project.entity.ProductEntity;
import org.hibernate.sql.Insert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;


public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    //책 목록 보기
    Page<ProductEntity> findByBookidContainingOrBooknameContainingOrCategorynumberContainingOrPublisher
    (String keyword1,String keyword2,String keyword3, String keyword4,Pageable pageable);

    @Query(value="select * from tbl_product", nativeQuery=true)
    List<ProductEntity> getProductList();



    @Query(value="select bookname from tbl_product where bookid = :bookid", nativeQuery = true)
    String getBookName(@Param("bookid") String bookid);




    // bookname, cover 가져오기 -> 나중에 interests 토대로 가져올 예정
    @Query(value = "select * from tbl_product where categorynumber = (select categorynumber from tbl_category where depth3 = :interest)", nativeQuery = true)
    List<ProductEntity> productlist(@Param("interest") String interest);



    // bookname, cover 가져오기 -> 나중에 interests 토대로 가져올 예정
    @Query(value = "select * from tbl_product ", nativeQuery = true)
    List<ProductEntity> productlist1();






}
