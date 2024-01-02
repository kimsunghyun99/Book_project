package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.PurchaseStatusEntity;
import com.book.book_project.entity.UnMemberEntity;
import com.book.book_project.entity.repository.PurchaseInfoRepository;
import com.book.book_project.entity.repository.PurchaseStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PurchaseInfoServiceImpl implements PurchaseInfoService {
    private final PurchaseInfoRepository purchaseInfoRepository;



//    @Override
//    public List<PurchaseInfoEntity> purchaseList(BuyerInfoEntity buyerseq){
//
//
//        return repository.findByBuyerseq(buyerseq);
//    }


    @Override
    public PurchaseInfoEntity purchaseList( BuyerInfoEntity buyerseq){


        return (PurchaseInfoEntity) purchaseInfoRepository.findByBuyerseq(buyerseq);
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



    //회원 주문 관리
    public List<Map<String, String>> mempurchaseinfo(){
        List<Map<String, String>> list = purchaseInfoRepository.mempurchaseinfo();

        return list;
    }

    //비회원 주문 관리
    public List<Map<String, String>> unpurchaseinfo(){
        List<Map<String, String>> list = purchaseInfoRepository.unpurchaseinfo();

        return list;
    }

    //회원 주문 상태 변경
    public void memberorderupdate(int statusseq, int purchaseinfonumber){
        purchaseInfoRepository.memberorderupdate(statusseq, purchaseinfonumber);
    }

    //비회원 주문 상태 변경
    public void unmemberorderupdate(int statusseq, int unmemberpurchaseinfoseq) {
        purchaseInfoRepository.unmemberorderupdate(statusseq, unmemberpurchaseinfoseq);
    }
}
