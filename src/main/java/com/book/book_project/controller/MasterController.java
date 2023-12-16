package com.book.book_project.controller;


import com.book.book_project.dto.ProductDTO;
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
import java.util.ArrayList;
import java.sql.Timestamp;


@Controller
@RequiredArgsConstructor
public class MasterController {

    private final ProductService productService;

    //책 업데이트
    @GetMapping("/master/bookUpdate")
    public void getBookUpdate() throws Exception {
        String key = "ttbdpfwnl01191710001";
        String result = "";
        URL url = new URL("http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey="+key+"&Query = 검색키워드 &QueryType=Title" +
                "&MaxResult=50&start=1&output=js&Version=20131101");
        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONArray item = (JSONArray) jsonObject.get("item");

        ArrayList<ProductDTO> itemList = new ArrayList<>();

        for(int i=0; i<item.size(); i++){
            ProductDTO dto = new ProductDTO();

            JSONObject itemInfo = (JSONObject) item.get(i);
            dto.setBookname((String)itemInfo.get("title"));
            dto.setAuthor((String)itemInfo.get("author"));
            dto.setPublisher((String)itemInfo.get("publisher"));
            dto.setPrice((Integer)itemInfo.get("pricestandard"));
            dto.setStock((Integer)itemInfo.get("stockstatus"));
            dto.setDescription((String)itemInfo.get("description"));
            dto.setCover((String)itemInfo.get("cover"));
            dto.setRegdate(new Timestamp(System.currentTimeMillis()));
            dto.setIsbn((String)itemInfo.get("isbn"));
            dto.setStatus((String)itemInfo.get("stockstatus"));
            dto.setPublicationdate((Timestamp) itemInfo.get("pubdate"));
            dto.setSalespoint((Integer) itemInfo.get("salesPoint"));

            itemList.add(dto);


        }
        productService.setBookList(itemList);
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
