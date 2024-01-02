package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.PurchaseStatusEntity;
import com.book.book_project.entity.UnMemberEntity;
import org.springframework.data.repository.query.Param;

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
    public void unmemberorderupdate(int statusseq, int unmember_purseq);
}
