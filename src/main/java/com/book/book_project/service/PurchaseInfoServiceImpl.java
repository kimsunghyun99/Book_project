package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
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

    //회원 구매내역 불러오기(갯수)
    @Override
    public int purchasecount(String userid){
        return purchaseInfoRepository.purchasecount(userid);
    }

    //매출 내역 뽑기
    @Override
    public List<Map<String, String>> totalPrice() {
        List<Map<String, String>> list = purchaseInfoRepository.totalPrice();
        return list;
    }

    //전체 판매 수량, 전체 판매 금액 합계
    @Override
    public List<Map<String, String>> totalSalesPrice(){
        List<Map<String, String>> list = purchaseInfoRepository.totalSalesPrice();
        return list;
    }

    //카테고리 별 매출
    @Override
    public List<Map<String, Object>> totalcategory(){
        List<Map<String, Object>> list = purchaseInfoRepository.totalcategory();
        return list;
    }

    //일별 매출
    @Override
    public List<Map<String, Object>> findDailySales(){
        List<Map<String, Object>> list = purchaseInfoRepository.findDailySales();
        return list;
    }

    //월별 매출
    @Override
    public List<Map<String, Object>> findMonthlySales(){
        List<Map<String, Object>> list = purchaseInfoRepository.findDailySales();
        return list;
    }

    //연도별 매출
    @Override
    public List<Map<String, Object>> findYearlySales(){
        List<Map<String, Object>> list = purchaseInfoRepository.findDailySales();
        return list;
    }
}
