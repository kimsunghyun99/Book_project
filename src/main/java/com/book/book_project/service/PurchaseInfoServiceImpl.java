package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.UnMemberEntity;
import com.book.book_project.entity.repository.PurchaseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PurchaseInfoServiceImpl implements PurchaseInfoService {
    private final PurchaseInfoRepository purchaseInfoRepository;


    @Override
    public List<PurchaseInfoEntity> purchaseList(BuyerInfoEntity buyerseq){


        return purchaseInfoRepository.findByBuyerseq(buyerseq);
    }

    @Override
    public List<PurchaseInfoEntity> unMemberPurchaseList(UnMemberEntity unmembertelno) throws Exception {
        return null;
    }

//    //회원 비회원 주문 관리
//    @Override
//    public List<ProductEntity> purchaselist(){
//        List<ProductEntity> list = purchaseInfoRepository.purchaselist();
//        return list;
//    }

    //회원 주문 관리
    public List<Map<String, String>> mempurchaseinfo(){
        List<Map<String, String>> list = purchaseInfoRepository.mempurchaseinfo();

        return list;
    }
}
