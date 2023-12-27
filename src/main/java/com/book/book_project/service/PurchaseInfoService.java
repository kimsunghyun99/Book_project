package com.book.book_project.service;

import com.book.book_project.dto.CartDTO;
import com.book.book_project.dto.PurchaseInfoDTO;
import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.UnMemberEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface PurchaseInfoService {

    //회원 구매 번호 조회
    //public
    List<PurchaseInfoEntity> purchaseList(BuyerInfoEntity buyerseq) throws Exception;

    List<PurchaseInfoEntity> unMemberPurchaseList(UnMemberEntity unmembertelno) throws Exception;

    public void processPayment(PurchaseInfoEntity paymentInfo) throws Exception;

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
