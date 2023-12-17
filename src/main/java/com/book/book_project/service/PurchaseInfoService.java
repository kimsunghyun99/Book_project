package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.PurchaseInfoEntity;

import java.util.List;

public interface PurchaseInfoService {

    //비회원 구매 번호 조회
    //public
    List<PurchaseInfoEntity> purchaseList(BuyerInfoEntity buyerseq) throws Exception;
}
