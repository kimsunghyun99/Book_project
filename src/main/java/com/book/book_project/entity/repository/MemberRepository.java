package com.book.book_project.entity.repository;

import com.book.book_project.entity.FavoritesEntity;
import com.book.book_project.entity.MemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

    @Query(value="select userid from tbl_member where (telno=:telno and username=:username and birthday=:birthday)", nativeQuery = true)
    Optional<MemberEntity> findByTelno(@Param("telno") String telno, @Param("username") String username, @Param("birthday") String birthday);


    //구매, 주문 목록 갯수 구하기
    @Query(value="SELECT COUNT(*) FROM tbl_member m JOIN tbl_buyerInfo b ON m.userid = b.userid JOIN tbl_purchaseInfo p ON b.buyerseq = p.buyerseq WHERE m.userid = :userid", nativeQuery = true)
    Long countJoinedRecordsByUserId(@Param("userid") String userid);


    //리뷰 갯수 구하기
    @Query(value = "select count(*) from tbl_review r join tbl_member m on r.userid = m.userid where m.userid = :userid", nativeQuery = true)
    Long countReviewsByUserId(@Param("userid") String userid);

    // 회원 정보 변경
    @Transactional
    @Modifying //  테이블에 DML ( insert, update, delete) 을 실행 시켜 변화를 주었을 경우 테이블에 반영된 내용을 엔티티 클래스에 반영
    @Query(value="update tbl_member set username = :username, nickname = :nickname, telno = :telno where userid = :userid",nativeQuery = true)
    public void membermodify(@Param("userid") String userid, @Param("username") String username, @Param("nickname") String nickname, @Param("telno") String telno);
//// interests =:interests
}
