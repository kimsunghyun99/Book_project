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
import java.util.Optional;

@Transactional
@Repository
public interface UnMemberRepository extends JpaRepository<UnMemberEntity, String> {

    //비회원 정보 가져오기
    @Query(value = "select * from tbl_unmember where temppassword = :temppassword", nativeQuery = true)
    List<UnMemberEntity> findByTemppassword(String temppassword);

    //비회원 구매 내역 가져오기
    @Query(value = "select * from tbl_unmemberpurchaseinfo p join tbl_unmember u on p.unmemberseq = u.unmemberseq where u.receivertelno = :receivertelno", nativeQuery = true)
    List<UnMemberPurchaseInfoEntity> findByReceivertelno(String receivertelno);
}
