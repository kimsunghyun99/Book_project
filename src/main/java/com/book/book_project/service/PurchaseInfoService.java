package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.PurchaseStatusEntity;
import com.book.book_project.entity.UnMemberEntity;

import java.util.List;

public interface PurchaseInfoService {

    //회원 구매 번호 조회
    //public
//    List<PurchaseInfoEntity> purchaseList(BuyerInfoEntity buyerseq) throws Exception;
   PurchaseInfoEntity purchaseList( BuyerInfoEntity buyerseq) throws Exception;

    List<PurchaseInfoEntity> unMemberPurchaseList(UnMemberEntity unmembertelno) throws Exception;






    // 주문상태 변경하기
    public void updateStatusseq(int statsseq, int purchaseinfonumber) throws Exception;



//    String repository(String statusseq) throws Exception;
}
