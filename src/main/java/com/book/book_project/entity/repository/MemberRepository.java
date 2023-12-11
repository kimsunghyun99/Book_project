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


    @Query(value="select userid from tbl_member where (telno=:telno and username=:username and birthday=:birthday) or email=:email", nativeQuery = true)
    Optional<MemberEntity> findByTelnoOrEmail(@Param("telno") String telno, @Param("username") String username, @Param("birthday") int birthday, @Param("email") String email);


    //구매, 주문 목록 갯수 구하기
    @Query(value="SELECT COUNT(*) FROM tbl_member m JOIN tbl_buyerInfo b ON m.userid = b.userid JOIN tbl_purchaseInfo p ON b.buyerseq = p.buyerseq WHERE m.userid = :userid", nativeQuery = true)
    Long countJoinedRecordsByUserId(@Param("userid") String userid);

    //즐겨찾기 목록 보기
    @Query(value="SELECT * FROM tbl_favorite f JOIN tbl_member m ON f.userid = m.userid WHERE m.userid = :userid", nativeQuery = true)
    List<FavoritesEntity> findFavoritesByUserId(@Param("userid") String userid);
}
