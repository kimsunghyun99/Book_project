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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
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


        return purchaseInfoRepository.findByBuyerseq(buyerseq);
    }

    // bookid 를 토대로 statusseq 가져오기
    @Override
    public int GetStatusSeq(String bookid) throws Exception {
        return purchaseInfoRepository.GetStatusSeq(bookid);
    }

    // buyerseq 를 토대로 bookid 가져오기
    @Override
    public String GetBookId(int buyerseq) throws Exception {
        return purchaseInfoRepository.GetBookId(buyerseq);
    }


    @Override
    public List<PurchaseInfoEntity> unMemberPurchaseList(UnMemberEntity unmembertelno) throws Exception {
        return null;
    }

    // 주문상태 변경하기
    @Override
    public void updateStatusseq(int statsseq, int purchaseinfonumber) throws Exception {
        System.out.println("서비스임플1");
        purchaseInfoRepository.updateStatusseq(statsseq,  purchaseinfonumber );
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
    @Override
    public void processPayment(PurchaseInfoEntity paymentInfo) throws Exception {


    }

    @Value("${imp_key}")
    private String imp_key;

    @Value("${imp_secret}")
    private String imp_secret;

    @Override
    public String getToken() throws IOException {

        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/users/getToken");

        conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        JsonObject json = new JsonObject();




        json.addProperty("imp_key", imp_key);
        json.addProperty("imp_secret", imp_secret);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        bw.write(json.toString());
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        Gson gson = new Gson();

        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();

        System.out.println("response : " + response);

        String token = gson.fromJson(response, Map.class).get("access_token").toString();

        br.close();
        conn.disconnect();

        return token;
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
        List<Map<String, Object>> listA = purchaseInfoRepository.findDailySales();
        return listA;
    }

    //월별 매출
    @Override
    public List<Map<String, Object>> findMonthlySales(){
        List<Map<String, Object>> listB = purchaseInfoRepository.findDailySales();
        return listB;
    }

    //연도별 매출
    @Override
    public List<Map<String, Object>> findYearlySales(){
        List<Map<String, Object>> listC = purchaseInfoRepository.findDailySales();
        return listC;
    }
}
