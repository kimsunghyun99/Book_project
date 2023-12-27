package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.UnMemberEntity;
import com.book.book_project.entity.repository.PurchaseInfoRepository;

import lombok.RequiredArgsConstructor;

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
    private final PurchaseInfoRepository repository;

    @Override
    public List<PurchaseInfoEntity> purchaseList(BuyerInfoEntity buyerseq){


        return repository.findByBuyerseq(buyerseq);
    }

    @Override
    public List<PurchaseInfoEntity> unMemberPurchaseList(UnMemberEntity unmembertelno) throws Exception {
        return null;
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

}
