package com.book.book_project.entity.repository;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.dto.UnMemberDTO;
import com.book.book_project.dto.UnMemberPurchaseInfoDTO;
import com.book.book_project.entity.UnMemberEntity;
import com.book.book_project.entity.UnMemberPurchaseInfoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Repository
public interface UnMemberRepository extends JpaRepository<UnMemberEntity, String> {

    //비회원 정보 가져오기
    @Query(value = "select * from tbl_unmember where temppassword = :temppassword", nativeQuery = true)
    List<UnMemberEntity> findByTemppassword(String temppassword);

    //비회원 전화번호 가져오기
    @Query(value = "select * from tbl_unmember where receivertelno = receivertelno", nativeQuery = true)
    List<UnMemberEntity> findByReceivertelno(String receivertelno);

    //비회원 구매 목록 가져오기
    @Query(value = "SELECT * \n" +
            "FROM tbl_unmemberpurchaseinfo ump \n" +
            "JOIN tbl_unmember u ON ump.unmemberseq = u.unmemberseq\n" +
            "JOIN tbl_product p ON ump.bookid = p.bookid \n" +
            "JOIN tbl_purchasestatus pp ON ump.statusseq = pp.statusseq\n" +
            "WHERE u.receivertelno = :receivertelno", nativeQuery = true)
    List<Map<String, String>> unmempurchaseList(@Param("receivertelno") String receivertelno);


    UnMemberEntity findByUnmemberseq(int unmemerseq);

}
