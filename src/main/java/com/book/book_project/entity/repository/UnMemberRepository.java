package com.book.book_project.entity.repository;

import com.book.book_project.dto.UnMemberDTO;
import com.book.book_project.entity.UnMemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface UnMemberRepository extends JpaRepository<UnMemberEntity, String> {

    //비회원 주문 번호 가져오기
    @Query(value = "select purchaseinfonumber from tbl_purchaseinfo p join tbl_unmember u on p.unmemberseq = u.unmemberseq where u.unmemberseq = :unmemberseq", nativeQuery = true)
    public Integer findByUnmemberseq(int unmemberseq);
}
