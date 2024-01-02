package com.book.book_project.service;

import com.book.book_project.dto.CartDTO;
import com.book.book_project.dto.PurchaseInfoDTO;
import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.PurchaseStatusEntity;
import com.book.book_project.entity.UnMemberEntity;
import org.springframework.data.repository.query.Param;
import jakarta.servlet.http.HttpSession;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

public interface PurchaseInfoService {

    //회원 구매 번호 조회
    //public
//    List<PurchaseInfoEntity> purchaseList(BuyerInfoEntity buyerseq) throws Exception;
   PurchaseInfoEntity purchaseList( BuyerInfoEntity buyerseq) throws Exception;

    List<PurchaseInfoEntity> unMemberPurchaseList(UnMemberEntity unmembertelno) throws Exception;






    // 주문상태 변경하기
    public void updateStatusseq(int statsseq, int purchaseinfonumber) throws Exception;



//    String repository(String statusseq) throws Exception;

    //회원 주문 관리
    public List<Map<String, String>> mempurchaseinfo();

    //비회원 주문 관리
    public List<Map<String, String>> unpurchaseinfo();

    //회원 주문 상태 변경
    public void memberorderupdate(int statusseq, int purchaseinfonumber);

    //비회원 주문 상태 변경
    public void unmemberorderupdate(int statusseq, int unmemberpurchaseinfoseq);

    //회원 구매내역 불러오기(갯수)
    public int purchasecount(String userid);

    //매출 내역 뽑기
    public List<Map<String, String>> totalPrice();

    //전체 판매 수량, 전체 판매 금액 합계
    public List<Map<String, String>> totalSalesPrice();

    //카테고리 별 매출
    public List<Map<String, Object>> totalcategory();

    //일별 매출
    public List<Map<String, Object>> findDailySales();

    //월별 매출
    public List<Map<String, Object>> findMonthlySales();

    //연도별 매출
    public List<Map<String, Object>> findYearlySales();


    public void processPayment(PurchaseInfoEntity paymentInfo) throws Exception;

    String getToken() throws Exception;



//    String getToken();
//
//    public int paymentInfo(int purchaseinfonumber, String token)throws Exception;
//
//    public long orderPriceCheck(CartDTO cartList)throws Exception;
//
//
//    public void payMentCancle(String token, int purchaseinfonumber, int amount, String 결제_금액_오류)throws Exception;
//
//    void order(CartDTO cartList, PurchaseInfoDTO orderInfo, UserDetails user, HttpSession session)throws Exception;

}
