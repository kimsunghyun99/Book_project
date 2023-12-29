package com.book.book_project.entity.repository;

import com.book.book_project.dto.ProductDTO;
import com.book.book_project.entity.CategoryEntity;
import com.book.book_project.entity.ProductEntity;
import org.hibernate.sql.Insert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;


public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    //책 목록 보기 ( interest를 고른경우)
//    public Page<ProductEntity> findByBooknameContainingOrAuthorContainingOrPublisherContainingAndCategorynumber_Categorynumber
//    (String keyword1, String keyword2, String keyword3, int categorynumber, Pageable pageable);

    @Query(value = "SELECT p.bookid,p.author,p.bookname,p.cover,p.description,p.price,p.pricesales, p.publicationdate,p.publisher,p.regdate,p.salespoint,p.stock,p.categorynumber FROM tbl_product p WHERE (p.bookname LIKE %:keyword% OR p.author LIKE %:keyword% OR p.publisher LIKE %:keyword%) AND categorynumber IN :categorynumber", nativeQuery = true)
    Page<ProductEntity> findByKeywordAndCategorynumber(@Param("keyword") String keyword,@Param("categorynumber") List<Integer> categorynumbers, Pageable pageable);



    //책 목록 보기 ( all)
    public Page<ProductEntity> findByBooknameContainingOrAuthorContainingOrPublisherContaining
    (String keyword1,String keyword2,String keyword3, Pageable pageable);



//    List<Object> findById(ProductEntity bookid);

    //bookid 가져 오기
    @Query(value="select * from tbl_product", nativeQuery=true)
    String getBookid();

//    //
    @Query(value="select * from tbl_product", nativeQuery=true)
    List<ProductEntity> getProductList();


    @Query(value="select categorynumber from tbl_category where depth3 = :interest", nativeQuery = true)
    List<Integer> getCateNumber(@Param("interest") String interest);




    // totalcount 꺼내기위함 (interest에 맞는)
//    @Query(value="select count(*) from tbl_product where categorynumber IN (select categorynumber from tbl_category where depth2 = :interest)", nativeQuery = true)
//    int bookcount(@Param("interest") String interest);


    @Query(value="select bookname from tbl_product where bookid = :bookid", nativeQuery = true)
    String getBookName(@Param("bookid") String bookid);





    @Query(value = "select * from tbl_product where categorynumber IN (select categorynumber from tbl_category where depth3 = :interest)", nativeQuery = true)
    List<ProductEntity> productlist(@Param("interest") String interest);




    @Query(value = "select * from tbl_product ", nativeQuery = true)
    List<ProductEntity> productlist1();


    @Query(value = "select * from tbl_product where categorynumber IN (select categorynumber from tbl_category where depth2 = :interest)", nativeQuery = true)
    List<ProductEntity> productlist2(@Param("interest") String interest);



}
