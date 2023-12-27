package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.UnMemberEntity;
import com.book.book_project.entity.repository.PurchaseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseInfoServiceImpl implements PurchaseInfoService {
    private final PurchaseInfoRepository repository;


    @Override
    public List<PurchaseInfoEntity> purchaseList(BuyerInfoEntity buyerseq){


        return repository.findByBuyerseq(buyerseq);
    }

    @Override
    public List<PurchaseInfoEntity> unMemberPurchaseList(UnMemberEntity unmembertelno) throws Exception {
        return null;
    }

    //회원 / 비회원 주문 관리
    @Override
    public List<ProductEntity> purchaselist(){
        List<ProductEntity> list = repository.purchaselist();
        return list;
    }

}
