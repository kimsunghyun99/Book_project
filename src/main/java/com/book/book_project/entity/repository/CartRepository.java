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



    // 장바구니로 가기 전 해당 id 불러오기 (Memberentity형인 userid를 가져오기 위함)
    @Query(value="select userid from tbl_cart", nativeQuery=true)
    List<CartEntity> getCartList();


    // 장바구니에 있는 해당 상품 개수 세기
    @Query(value = "SELECT COALESCE(MAX(cartvolume), 0) FROM tbl_cart WHERE userid = :userid AND bookid = :bookid", nativeQuery = true)
    int bCartQuantity(@Param("userid") String userid, @Param("bookid") String bookid);

    //  장바구니에서 해당되는 bookid에 대한 것을  수 업데이트 하기위해 사용
    public CartEntity findByBookidAndUserid(ProductEntity book, MemberEntity user);

    @Query(value = "select cartid, cartvolume, bookid, userid from tbl_cart where userid=':userid'", nativeQuery = true)
    List<CartDTO> findByUserid(@Param("userid") String userid);

    // 장바구니안에 숫자 세기
    @Query(value = "select count(*) from tbl_cart where userid = :userid", nativeQuery = true)
    int bCartCount(@Param("userid") String userid);

}
