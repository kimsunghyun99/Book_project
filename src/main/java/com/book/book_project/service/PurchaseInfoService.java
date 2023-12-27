package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.UnMemberEntity;

import java.util.List;

public interface PurchaseInfoService {

    //회원 구매 번호 조회
    //public
    List<PurchaseInfoEntity> purchaseList(BuyerInfoEntity buyerseq) throws Exception;

    List<PurchaseInfoEntity> unMemberPurchaseList(UnMemberEntity unmembertelno) throws Exception;

    //회원 비회원 주문 관리
    public List<ProductEntity> purchaselist();
}
