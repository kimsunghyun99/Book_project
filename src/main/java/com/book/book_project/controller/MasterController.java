package com.book.book_project.controller;


import com.book.book_project.dto.ProductDTO;
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
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MasterController {

    private final ProductService productService;

    //책 업데이트
    @GetMapping("/master/bookUpdate")
    public void getBookUpdate() throws Exception {
        String key = "ttbdpfwnl01191710001";
        String categoryName = "컴퓨터/모바일"; // 원하는 카테고리 이름으로 변경
        URL url = new URL("http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=" + key + "&Query=" + URLEncoder.encode(categoryName, "UTF-8") + "&QueryType=Title" +
                "&MaxResults=50&start=1&output=js&Version=20131101");

        System.out.println(url);

        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
        String result = "";
        String line;
        while((line = bf.readLine()) != null) {
            result = result.concat(line);
        }
        bf.close();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONArray itemArray = (JSONArray) jsonObject.get("itemList");

        List<ProductEntity> productList = new ArrayList<>();

        if(itemArray != null) {
            for(Object itemObj : itemArray) {
                JSONObject itemInfo = (JSONObject) itemObj;
                ProductEntity product = new ProductEntity();

                product.setBookname((String) itemInfo.get("title"));
                product.setAuthor((String) itemInfo.get("author"));
                product.setPublisher((String) itemInfo.get("publisher"));
                product.setPrice((Integer) itemInfo.get("pricestandard"));
                product.setStock((Integer) itemInfo.get("stockstatus"));
                product.setDescription((String) itemInfo.get("description"));
                product.setCover((String) itemInfo.get("cover"));
                product.setRegdate(new Timestamp(System.currentTimeMillis()));
                product.setIsbn((String) itemInfo.get("isbn"));
                product.setBookid((String) itemInfo.get("isbn"));
                product.setStatus((String) itemInfo.get("stockstatus"));
                product.setPublicationdate((Timestamp) itemInfo.get("pubdate"));
                product.setSalespoint((Integer) itemInfo.get("salesPoint"));

                productList.add(product);
            }

        }
        productService.setBookList(productList);
        System.out.println(productList);
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
