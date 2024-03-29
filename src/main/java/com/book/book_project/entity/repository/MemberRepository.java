package com.book.book_project.entity.repository;

import com.book.book_project.entity.MemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
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
    @Query(value="update tbl_member set username = :username, nickname = :nickname, telno = :telno, interest = :interest where userid = :userid",nativeQuery = true)
    public void membermodify(@Param("userid") String userid, @Param("username") String username, @Param("nickname") String nickname, @Param("telno") String telno, @Param("interest") String interest);

    //social로그인 회원 수 불러오기
    @Query(value = "select count(*) as social from tbl_member where fromsocial = (\"Y\") ", nativeQuery = true)
        public int socialcount();

    //일반 로그인 회원 수 불러오기
    @Query(value = "select count(*) as normal from tbl_member where fromsocial = (\"N\")", nativeQuery = true)
    public int normalcount();

    //일반 회원 나이대별 회원 수 불러오기
    @Query(value="SELECT d.age_group, IFNULL(member_ages.count, 0) AS count \n" +
            "FROM (\n" +
            "    SELECT 'cnt10' AS age_group, NULL AS count\n" +
            "    UNION ALL SELECT 'cnt20', NULL\n" +
            "    UNION ALL SELECT 'cnt30', NULL\n" +
            "    UNION ALL SELECT 'cnt40', NULL\n" +
            "    UNION ALL SELECT 'cnt50', NULL\n" +
            ") AS d\n" +
            "LEFT JOIN (\n" +
            "    SELECT \n" +
            "        CASE\n" +
            "            WHEN TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 10 AND 19 THEN 'cnt10'\n" +
            "            WHEN TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 20 AND 29 THEN 'cnt20'\n" +
            "            WHEN TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 30 AND 39 THEN 'cnt30'\n" +
            "            WHEN TIMESTAMPDIFF(YEAR, birthday, CURDATE()) BETWEEN 40 AND 49 THEN 'cnt40'\n" +
            "            WHEN TIMESTAMPDIFF(YEAR, birthday, CURDATE()) >= 50 THEN 'cnt50'\n" +
            "        END AS age_group,\n" +
            "        COUNT(*) as count\n" +
            "    FROM tbl_member\n" +
            "    WHERE fromsocial = ('N')\n" +
            "    GROUP BY age_group\n" +
            ") AS member_ages\n" +
            "ON d.age_group = member_ages.age_group;" ,nativeQuery = true)
    public List<Map<String, Integer>> memberage();

    //전체 회원 목록 불러오기
    Page<MemberEntity> findByRole(String role, Pageable pageable);

    //회원 구매 순위
    @Query(value = "SELECT m.username, SUM(p.total_price) AS total\n" +
            "FROM tbl_purchaseinfo p\n" +
            "JOIN tbl_buyerinfo b ON p.buyerseq = b.buyerseq\n" +
            "JOIN tbl_member m ON b.userid = m.userid\n" +
            "WHERE p.statusseq = 11\n" +
            "GROUP BY m.username\n" +
            "ORDER BY total DESC\n" +
            "LIMIT 3", nativeQuery = true)
    List<Map<String, String>> memberRank();

//    @Query(value = "SELECT SUM(point) FROM tbl_member", nativeQuery = true)
//    public int getPoint(String userid);
MemberEntity findByUserid(String userid);
}
