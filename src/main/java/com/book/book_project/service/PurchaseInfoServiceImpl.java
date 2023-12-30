package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.PurchaseStatusEntity;
import com.book.book_project.entity.UnMemberEntity;
import com.book.book_project.entity.repository.PurchaseInfoRepository;
import com.book.book_project.entity.repository.PurchaseStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseInfoServiceImpl implements PurchaseInfoService {
    private final PurchaseInfoRepository repository;



//    @Override
//    public List<PurchaseInfoEntity> purchaseList(BuyerInfoEntity buyerseq){
//
//
//        return repository.findByBuyerseq(buyerseq);
//    }


    @Override
    public PurchaseInfoEntity purchaseList( BuyerInfoEntity buyerseq){


        return repository.findByBuyerseq(buyerseq);
    }


    @Override
    public List<PurchaseInfoEntity> unMemberPurchaseList(UnMemberEntity unmembertelno) throws Exception {
        return null;
    }

    // 주문상태 변경하기
    @Override
    public void updateStatusseq(int statsseq, int purchaseinfonumber) throws Exception {
        System.out.println("서비스임플1");
        repository.updateStatusseq(statsseq,  purchaseinfonumber );
        System.out.println("서비스임플2");
    }



}
