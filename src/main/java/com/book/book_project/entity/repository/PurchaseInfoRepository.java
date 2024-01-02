package com.book.book_project.entity.repository;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.PurchaseStatusEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Transactional
@Repository
public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfoEntity, Integer> {

    List<PurchaseInfoEntity> findByBuyerseq(BuyerInfoEntity buyerseq);
    //주문 상태 불러오기
    @Query(value = "select * from tbl_purchasestatus", nativeQuery = true)
    List<Map<String,String>> statuslist();

    //회원 주문 관리
    @Query(value = "SELECT p.* ,pr.*, b.* , s.*\n" +
            "FROM tbl_purchaseinfo p\n" +
            "JOIN tbl_buyerinfo b ON p.buyerseq = b.buyerseq\n" +
            "JOIN tbl_product pr ON p.bookid = pr.bookid\n" +
            "JOIN tbl_purchasestatus s ON p.statusseq = s.statusseq;", nativeQuery = true)
    List<Map<String, String>> mempurchaseinfo();

    //비회원 주문 관리
    @Query(value = "SELECT u.*, p.* , um.*, s.*\n" +
            "FROM tbl_unmemberpurchaseinfo u\n" +
            "JOIN tbl_unmember um ON u.unmemberseq = um.unmemberseq\n" +
            "JOIN tbl_product p ON u.bookid = p.bookid\n" +
            "JOIN tbl_purchasestatus s on u.statusseq = s.statusseq", nativeQuery = true)
    List<Map<String, String>> unpurchaseinfo();

    //회원 주문 상태 변경
    @Transactional
    @Modifying//테이블 DML(update, insert, delete)을 실행 시켜 변화를 주었을 경우 테이블에 반영된 내용을 엔티티 클래스에 반영
    @Query(value = "update tbl_purchaseinfo set statusseq = :statusseq where purchaseinfonumber = :purchaseinfonumber", nativeQuery = true)
    public void memberorderupdate(@Param("statusseq") int statusseq, @Param("purchaseinfonumber") int purchaseinfonumber);

    //비회원 주문 상태 변경
    //회원 주문 상태 변경
    @Transactional
    @Modifying//테이블 DML(update, insert, delete)을 실행 시켜 변화를 주었을 경우 테이블에 반영된 내용을 엔티티 클래스에 반영
    @Query(value = "update tbl_unmemberpurchaseinfo set statusseq = :statusseq where unmemberpurchaseinfoseq = :unmemberpurchaseinfoseq", nativeQuery = true)
    public void unmemberorderupdate(@Param("statusseq") int statusseq, @Param("unmemberpurchaseinfoseq") int unmemberpurchaseinfoseq);

    //회원 구매내역 불러오기(갯수)
    @Query(value = "SELECT COUNT(*) \n" +
            "FROM tbl_purchaseinfo p \n" +
            "join tbl_buyerinfo b ON p.buyerseq = b.buyerseq \n" +
            "JOIN tbl_member m ON b.userid = m.userid\n" +
            "WHERE m.userid = :userid", nativeQuery = true)
    public int purchasecount(@Param("userid") String userid);

    //매출 내역 뽑기
    @Query(value = "select pp.*, p.total_price , p.volume  \n" +
            "from tbl_purchaseinfo p \n" +
            "join tbl_product pp on p.bookid = pp.bookid \n" +
            "where p.statusseq = 5\n" +
            "UNION\n" +
            "select pp.* , ub.total_price , ub.volume \n" +
            "from tbl_unmemberpurchaseinfo ub\n" +
            "join tbl_product pp on ub.bookid = pp.bookid \n" +
            "where ub.statusseq = 5", nativeQuery = true)
    List<Map<String, String>> totalPrice();

    //전체 판매 수량, 전체 판매 금액 합계
    @Query(value = "SELECT \n" +
            "  (SELECT sum(total_price) FROM tbl_purchaseinfo WHERE statusseq = 5) +\n" +
            "  (SELECT sum(total_price) FROM tbl_unmemberpurchaseinfo WHERE statusseq = 5) AS totalsales,\n" +
            "  (select sum(volume) from tbl_purchaseinfo where statusseq = 5) +\n" +
            "  (select sum(volume) from tbl_unmemberpurchaseinfo where statusseq = 5) as totalvolume", nativeQuery = true)
    List<Map<String, String>> totalSalesPrice();

    //카테고리 별 매출(일별 매출)
    @Query(value = "SELECT c.categoryname, SUM(pu.total_price) AS total_sales, SUM(pu.volume) AS total_volume\n" +
            "FROM tbl_purchaseinfo pu\n" +
            "JOIN tbl_product p ON pu.bookid = p.bookid\n" +
            "JOIN tbl_category c ON p.categorynumber = c.categorynumber\n" +
            "WHERE pu.statusseq = 5\n" +
            "GROUP BY c.categoryname\n" +
            "union\n" +
            "SELECT c.categoryname, SUM(um.total_price) AS total_sales, SUM(um.volume) AS total_volume\n" +
            "FROM tbl_unmemberpurchaseinfo um\n" +
            "JOIN tbl_product p ON um.bookid = p.bookid\n" +
            "JOIN tbl_category c ON p.categorynumber = c.categorynumber\n" +
            "WHERE um.statusseq = 5\n" +
            "GROUP BY c.categoryname", nativeQuery = true)
    List<Map<String, Object>> totalcategory();
/*=========================================================================================================================*/
    // 일별 매출
    @Query(value = "SELECT DATE(purchasedate) AS DATE, c.categoryname, SUM(pu.total_price) AS total_sales, SUM(pu.volume) AS total_volume\n" +
            "FROM tbl_purchaseinfo pu\n" +
            "JOIN tbl_product p ON pu.bookid = p.bookid\n" +
            "JOIN tbl_category c ON p.categorynumber = c.categorynumber\n" +
            "WHERE pu.statusseq = 5\n" +
            "GROUP BY DATE(purchasedate)\n" +
            "union\n" +
            "SELECT DATE(purchasedate) AS DATE, c.categoryname, SUM(um.total_price) AS total_sales, SUM(um.volume) AS total_volume\n" +
            "FROM tbl_unmemberpurchaseinfo um\n" +
            "JOIN tbl_product p ON um.bookid = p.bookid\n" +
            "JOIN tbl_category c ON p.categorynumber = c.categorynumber\n" +
            "WHERE um.statusseq = 5\n" +
            "GROUP BY DATE(purchasedate)", nativeQuery = true)
    List<SalesData> findDailySales();

    //월별 매출
    @Query(value = "SELECT CONCAT(YEAR(purchasedate), '-', MONTH(purchasedate)) AS month, SUM(pu.total_price) AS total_sales, SUM(pu.volume) AS total_volume, c.categoryname\n" +
            "FROM tbl_purchaseinfo pu\n" +
            "JOIN tbl_product p ON pu.bookid = p.bookid\n" +
            "JOIN tbl_category c ON p.categorynumber = c.categorynumber\n" +
            "WHERE pu.statusseq = 5\n" +
            "GROUP BY YEAR(purchasedate), MONTH(purchasedate)\n" +
            "union\n" +
            "SELECT CONCAT(YEAR(purchasedate), '-', MONTH(purchasedate)) AS month, SUM(um.total_price) AS total_sales, SUM(um.volume) AS total_volume ,c.categoryname\n" +
            "FROM tbl_unmemberpurchaseinfo um\n" +
            "JOIN tbl_product p ON um.bookid = p.bookid\n" +
            "JOIN tbl_category c ON p.categorynumber = c.categorynumber\n" +
            "WHERE um.statusseq = 5\n" +
            "GROUP BY YEAR(purchasedate), MONTH(purchasedate)", nativeQuery = true)
    List<SalesDate> findMonthlySales();

    //년도별 매출
    @Query(value = "SELECT YEAR(purchasedate) AS year, SUM(pu.total_price) AS total_sales, SUM(pu.volume) AS total_volume, c.categoryname\n" +
            "FROM tbl_purchaseinfo pu\n" +
            "JOIN tbl_product p ON pu.bookid = p.bookid\n" +
            "JOIN tbl_category c ON p.categorynumber = c.categorynumber\n" +
            "WHERE pu.statusseq = 5\n" +
            "GROUP BY YEAR(purchasedate)\n" +
            "union\n" +
            "SELECT YEAR(purchasedate) AS year, SUM(um.total_price) AS total_sales, SUM(um.volume) AS total_volume ,c.categoryname\n" +
            "FROM tbl_unmemberpurchaseinfo um\n" +
            "JOIN tbl_product p ON um.bookid = p.bookid\n" +
            "JOIN tbl_category c ON p.categorynumber = c.categorynumber\n" +
            "WHERE um.statusseq = 5\n" +
            "GROUP BY YEAR(purchasedate)", nativeQuery = true)
    List<SalesDate> findYearlySales();

//    List<PurchaseInfoEntity> findByBuyerseq(BuyerInfoEntity buyerseq);
//PurchaseInfoEntity findByBuyerseq( BuyerInfoEntity buyerseq);

}
