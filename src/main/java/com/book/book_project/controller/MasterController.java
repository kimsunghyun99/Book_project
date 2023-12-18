package com.book.book_project.controller;



import com.book.book_project.entity.CategoryEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.repository.ProductRepository;
import com.book.book_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MasterController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    //책 업데이트
    @GetMapping("/master/bookUpdate")
    public void getBookUpdate() throws Exception {
        String key = "ttbdpfwnl01191710001";
        String result="";
        String title = "자바";
        ArrayList<ProductEntity> itemList = new ArrayList<>();

        URL url = new URL("http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey="+key+
                "&Query="+title+"&QueryType=Title&MaxResults-50&start=1&output=js&Version=20131101");
        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
        result = bf.readLine();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONArray item = (JSONArray) jsonObject.get("item");

        for(int i=0; i<item.size();i++){
            ProductEntity entity = new ProductEntity();
            JSONObject itemInfo = (JSONObject) item.get(i);

            entity.setBookname((String) itemInfo.get("title"));
            entity.setAuthor((String) itemInfo.get("author"));
            entity.setPublisher((String) itemInfo.get("publisher"));
            entity.setPrice((Integer) itemInfo.get("priceStandard"));
            entity.setDescription((String) itemInfo.get("description"));
            entity.setCover((String) itemInfo.get("cover"));
            entity.setRegdate(new Timestamp(System.currentTimeMillis()));
            entity.setBookid((String) itemInfo.get("isbn"));
            entity.setPublicationdate((String) itemInfo.get("pubdate"));
            entity.setSalespoint((Integer) itemInfo.get("salesPoint"));
            entity.setCategorynumber((CategoryEntity) "2732");


            itemList.add(entity);

            productRepository.save(entity);

        }

        System.out.println(itemList.size()+"\n");
        for(int i=0; i<itemList.size(); i++){
            System.out.println(itemList.get(i));
        }



    }

    // 나이대별 통계
    @GetMapping("/master/ageStatistics")
    public void getAgeStatistics() {}


    // 장르별 통계
    @GetMapping("/master/genreStatistics")
    public void getGenreStatistics() {}

    // 회원 관리
    @GetMapping("/master/memberManage")
    public void getMemberManage() {}

    // 주문 확인
    @GetMapping("/master/purchaseManage")
    public void getPurchaseManage() {}


    // 매출 내역
    @GetMapping("/master/salesInfo")
    public void getSalesInfo() {}




}
