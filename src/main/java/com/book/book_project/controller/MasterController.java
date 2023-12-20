package com.book.book_project.controller;


import com.book.book_project.dto.ProductDTO;
import com.book.book_project.entity.CategoryEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.repository.CategoryRepository;
import com.book.book_project.entity.repository.ProductRepository;
import com.book.book_project.service.ProductService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class MasterController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/master/bookUpdate")
    public void getBookUpdate() throws Exception {
        String key = "ttbdpfwnl01191710001";
        String title = "프로그래밍"; // 검색하려는 제목
        URL url = new URL("http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=" + key
                + "&Query=" + URLEncoder.encode(title, "UTF-8") + "&QueryType=Title"
                + "&MaxResults=50&start=1&output=js&Version=20131101");



        System.out.println(url);

        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
        String result = "";
        String line;
        while((line = bf.readLine()) != null) {
            result = result.concat(line);
        }
        System.out.println(result);

        bf.close();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONArray itemArray = (JSONArray) jsonObject.get("item");
        System.out.println(jsonObject);
        List<ProductEntity> productList = new ArrayList<>();

        if(itemArray != null) {
            for(Object itemObj : itemArray) {
                JSONObject itemInfo = (JSONObject) itemObj;
                ProductEntity product = new ProductEntity();

                product.setBookname((String) itemInfo.get("title"));
                product.setAuthor((String) itemInfo.get("author"));
                product.setPublisher((String) itemInfo.get("publisher"));
                product.setPrice((Integer) itemInfo.get("priceStandard"));
                product.setStock((String) itemInfo.get("stockStatus"));
                product.setDescription((String) itemInfo.get("description"));
                product.setCover((String) itemInfo.get("cover"));
                product.setRegdate(new Timestamp(System.currentTimeMillis()));
                product.setBookid((String) itemInfo.get("isbn"));
                product.setPublicationdate((String) itemInfo.get("pubDate"));
                product.setSalespoint((Integer) itemInfo.get("salesPoint"));
                // api에서 받아온 categoryId를 int로 변환하여 설정
                int categoryId = (Integer) itemInfo.get("categoryId");

                // categoryDB에서 categoryId에 해당하는 데이터 조회
                CategoryEntity category = categoryRepository.findById(categoryId);

                if (null != category) {
                    product.setCategorynumber(category);
                    System.out.println("if 조건 안 null이 아닌 경우 :  " + category.getCategorynumber());
                    productList.add(product);
                    productRepository.save(product); // DB에 저장

                }
                //System.out.println("if 조건 밖 null인 경우 :  "+ category.getCategorynumber());

            }
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
