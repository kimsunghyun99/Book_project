package com.book.book_project.entity.repository;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.UnMemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BuyerInfoRepository extends JpaRepository<BuyerInfoEntity, Integer> {

    //비회원 구매번호 조회
    @Query(value = "SELECT * FROM tbl_unmember u JOIN tbl_buyerInfo b ON u.unmembertelno = b.unmembertelno JOIN tbl_purchaseInfo p ON b.buyerseq = p.buyerseq WHERE u.temppassword = :temppassword AND p.purchaseinfoseq = :purchaseinfoseq", nativeQuery = true)
    Optional<UnMemberEntity> findByTemppasswordAndPurchaseinfoseq(@Param("temppassword") String temppassword, @Param("purchaseinfoseq") int purchaseinfoseq);

    List<BuyerInfoEntity> findByUserid(MemberEntity userid);

    BuyerInfoEntity findByBuyerseq(int buyerseq);
}