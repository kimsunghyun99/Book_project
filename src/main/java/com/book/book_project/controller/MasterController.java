package com.book.book_project.controller;


import com.book.book_project.entity.*;
import com.book.book_project.entity.repository.CategoryRepository;
import com.book.book_project.entity.repository.MemberRepository;
import com.book.book_project.entity.repository.ProductRepository;
import com.book.book_project.entity.repository.PurchaseInfoRepository;
import com.book.book_project.service.MemberService;
import com.book.book_project.service.ProductService;
import com.book.book_project.service.PurchaseInfoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class MasterController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PurchaseInfoRepository purchaseInfoRepository;
    private final PurchaseInfoService purchaseInfoService;
    @GetMapping("/master/bookUpdate")
    public void getBookUpdate() throws Exception {
        String key = "ttbdpfwnl01191710001";
        String [] title = {
//                "MS 오피스","워드","엑셀","파워포인트","액세스","한글(한글과컴퓨터)","그래픽 일반","포토샵","3ds max","Maya"
//                ,"CAD","DTP","웹디자인 입문","웹기획","나모 웹에디터","드림위버","플래시","디렉터","프리미어/베가스","MySQL"
//                ,"윈도우 프로그래밍","Perl","전산학개론","소프트웨어 공학","알고리즘","전산수학","컴퓨터 공학","SPSS","CGI","MATLAB"
//                "네트워크","데이터 통신","마이크로프로세서","리눅스","유닉스","게임 프로그래밍","디지털문화","윈도우즈","매킨토시","Windows"
//                ,"MCP","MCSE","SCJP","Oracle","OCA","OCP","정보기기","정보통신","전자상거래","정보통신공학"
//                ,"CCNA","CCNP","일러스트레이터","포토샵","모바일","무선","임베디드","웹디자인","홈페이지","ASP"
//                ,"PHP","Visual C++","Visual Basic","HTML","JavaScript",".NET","C#","ASP.NET","Visual C++.NET","VisualBasic.NET"
//                ,".NET일반","네트워크 구축","리눅스","C","C++","MOS","MCAS","XML","객체지향 프로그래밍","UML"
//                ,"SQL","웹서비스","웹프로그래밍","ITQ","프로그래밍 기초","개발 방법론","파이썬","데이터베이스 개론","컬러리스트","사무자동화"
//                ,"델파이","정보처리기사","정보처리기능사","정보처리산업기사","프로그래밍 개발","방법론","JSP","네트워크 프로그래밍","코렐드로우","페인터"
//                ,"웹디자인 기능사","Ajax","Ruby","Rails","애플","아이폰","안드로이드","Object C","애플 어플리케이션","GTQ"
//                ,"Windows 7","Windows 8","스마트폰","태블릿","SNS","모바일 프로그래밍","아이폰","아이패드","안드로이드","모바일"
//                ,"임베디드","DIAT"
        };// 검색하려는 제목

         for(int i=0; i<title.length; i++){
            URL url = new URL("http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=" + key
                    + "&Query=" + URLEncoder.encode(title[i], "UTF-8") + "&QueryType=Title&"
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
    }
    // 회원 통계
    @GetMapping("/master/ageStatistics")
    public String socialcount(Model model){
        //소셜 / 일반 회원 통계
        int social = memberRepository.socialcount();
        int normal = memberRepository.normalcount();

        model.addAttribute("social", social);
        model.addAttribute("normal", normal);

        List<Map<String, Integer>> list = memberRepository.memberage();

        //일반 회원 가입연령 별 통계
        for (Map<String, Integer> map : list) {
            String ageGroup = String.valueOf(map.get("age_group"));
            Integer count = ((Number)map.get("count")).intValue();

            model.addAttribute(ageGroup, count);
        }

        return "/master/ageStatistics";

    }

    // 장르별 통계
    @GetMapping("/master/genreStatistics")
    public String getGenreStatistics(@RequestParam(name = "CategoryList", defaultValue = "day") String categoryList, Model model) {
        List<Map<String, Object>> totalCategoryList = purchaseInfoRepository.totalcategory();

        switch (categoryList) {
            case "day":
                totalCategoryList = purchaseInfoRepository.findDailySales();
                break;
            case "month":
                totalCategoryList = purchaseInfoRepository.findMonthlySales();
                break;
            case "year":
                totalCategoryList = purchaseInfoRepository.findYearlySales();
                break;
            default:
                totalCategoryList = purchaseInfoRepository.findDailySales(); // 디폴트 케이스 추가
                break;
        }

        model.addAttribute("totalCategoryList", totalCategoryList);
        return "/master/genreStatistics";
    }

    // 회원 관리
    @GetMapping("/master/memberManage")
    public String findAll(Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable adjustedPageable = PageRequest.of(page, 5);
        Page<MemberEntity> members = memberRepository.findByRole("USER", adjustedPageable);

        List<Map<String, String>> list = memberRepository.memberRank();
        List<Map<String, Object>> RankList = new ArrayList<>();

        for(Map<String, String> map : list){
            Map<String, Object> RankListMap = new HashMap<>();
            RankListMap.put("username", map.get("username"));
            RankListMap.put("total", map.get("total"));

            RankList.add(RankListMap);
        }
        model.addAttribute("RankList", RankList);
        model.addAttribute("members", members);
        return "/master/memberManage";
    }

    //회원 정지
    @PostMapping("/master/suspend")
    public String suspendMembers(@RequestParam("suspendMembers") String suspendMembers) throws Exception{
        // JSON 형태의 문자열을 List<String>으로 변환
        List<String> userids = new ObjectMapper().readValue(suspendMembers, new TypeReference<List<String>>(){});
        System.out.println(userids);
        memberService.stop(userids);

        return "redirect:/master/memberManage?page=0";
    }

    //회원 정지 해제
    @PostMapping("/master/unsuspend")
    public String unSuspendMembers(@RequestParam("unSuspendMembers") String unSuspendMembers) throws Exception {
        List<String> userids = new ObjectMapper().readValue(unSuspendMembers, new TypeReference<List<String>>() {});
        System.out.println(userids);
        memberService.stop(userids);
        return "redirect:/master/memberManage?page=0";
    }

    // 회원 주문 관리
    @GetMapping("/master/purchaseManage")
    public void purchaselist(Model model) {
        List<Map<String, String>> list = purchaseInfoRepository.mempurchaseinfo();
        List<Map<String, String>> list3 = purchaseInfoRepository.statuslist();
        List<Map<String, Object>> memPurchaseList = new ArrayList<>();

        for(Map<String, String> map : list){
            Map<String, Object> memPurchaseMap = new HashMap<>();
            //회원 주문 도서 정보
            memPurchaseMap.put("bookname", map.get("bookname"));
            memPurchaseMap.put("author", map.get("author"));
            memPurchaseMap.put("publisher", map.get("publisher"));
            memPurchaseMap.put("publicationdate", map.get("publicationdate"));
            memPurchaseMap.put("price", map.get("price"));
            memPurchaseMap.put("cover", map.get("cover"));
            memPurchaseMap.put("purchaseinfonumber", map.get("purchaseinfonumber"));
            memPurchaseMap.put("statusseq", map.get("statusseq"));
            memPurchaseMap.put("statusname",map.get("statusname"));

            //주문 회원 정보
            memPurchaseMap.put("username", map.get("receivername"));
            memPurchaseMap.put("userid", map.get("userid"));
            memPurchaseMap.put("addr1", map.get("receiveraddr"));
            memPurchaseMap.put("addr2", map.get("receiverdetailaddr"));
            memPurchaseMap.put("telno", map.get("receivertelno"));

            memPurchaseList.add(memPurchaseMap);
        }
        model.addAttribute("memPurchaseList", memPurchaseList);
        model.addAttribute("statuslist", list3);
    }

    //비회원 주문 관리
    @GetMapping("/master/unmemberPurchaseManage")
    public void unmemberpurchaselist(Model model){
        List<Map<String, String>> list2 = purchaseInfoRepository.unpurchaseinfo();
        List<Map<String, String>> list3 = purchaseInfoRepository.statuslist();

        List<Map<String, Object>> unmempurchaseList = new ArrayList<>();

        for(Map<String, String> map : list2){
            Map<String, Object> unmempurchaseMap = new HashMap<>();
            //비회원 주문 도서 정보
            unmempurchaseMap.put("bookname", map.get("bookname"));
            unmempurchaseMap.put("author", map.get("author"));
            unmempurchaseMap.put("publisher", map.get("publisher"));
            unmempurchaseMap.put("publicationdate", map.get("publicationdate"));
            unmempurchaseMap.put("price", map.get("price"));
            unmempurchaseMap.put("cover", map.get("cover"));
            unmempurchaseMap.put("unmemberpurchaseinfoseq", map.get("unmemberpurchaseinfoseq"));
            unmempurchaseMap.put("statusseq", map.get("statusseq"));
            unmempurchaseMap.put("statusname", map.get("statusname"));

            //비회원 정보
            unmempurchaseMap.put("name", map.get("receivername"));
            unmempurchaseMap.put("addr", map.get("addr"));
            unmempurchaseMap.put("detailaddr", map.get("detailaddr"));
            unmempurchaseMap.put("telnum", map.get("receivertelno"));

            unmempurchaseList.add(unmempurchaseMap);
        }
        model.addAttribute("unmempurchaseList", unmempurchaseList);
        model.addAttribute("statuslist", list3);
    }

    //회원 주문 상태 변경
    @ResponseBody
    @PostMapping("/master/memorderupdate")
    public String memberorderupdate(@RequestBody PurchaseInfoEntity purchaseInfoEntity){

        int statusseq = purchaseInfoEntity.getStatusseq().getStatusseq();
        int purchaseinfonumber = purchaseInfoEntity.getPurchaseinfonumber();

        purchaseInfoRepository.memberorderupdate(statusseq, purchaseinfonumber);

        return "good";
    }

    //비회원 주문 상태 변경
    @ResponseBody
    @PostMapping("/master/unmemorderupdate")
    public String unmemberorderupdate(@RequestBody UnMemberPurchaseInfoEntity unMemberPurchaseInfoEntity){

        int statusseq = unMemberPurchaseInfoEntity.getStatusseq().getStatusseq();
        int unmemberpurchaseinfoseq = unMemberPurchaseInfoEntity.getUnmemberpurchaseinfoseq();

        purchaseInfoRepository.unmemberorderupdate(statusseq, unmemberpurchaseinfoseq);

        return "good";
    }

    // 매출 내역
    @GetMapping("/master/salesInfo")
    public void getSalesInfo(Model model) {
        List<Map<String, String>> list = purchaseInfoRepository.totalPrice();
        List<Map<String, String>> list2 = purchaseInfoRepository.totalSalesPrice();

        List<Map<String, Object>> totalList = new ArrayList<>();
        List<Map<String, Object>> totalSalesPriceList = new ArrayList<>();

        for(Map<String, String> map : list){
            Map<String, Object> totalListMap = new HashMap<>();
            totalListMap.put("bookname", map.get("bookname"));
            totalListMap.put("price", map.get("price"));
            totalListMap.put("volume", map.get("volume"));
            totalListMap.put("total_price", map.get("total_price"));

            totalList.add(totalListMap);

        }

        for(Map<String, String> map2 : list2){
            Map<String, Object> totalSalesPriceMap = new HashMap<>();
            totalSalesPriceMap.put("totalsales", map2.get("totalsales"));
            totalSalesPriceMap.put("totalvolume", map2.get("totalvolume"));
            System.out.println(totalSalesPriceMap.put("totalsales", map2.get("totalsales")));
            System.out.println(totalSalesPriceMap.put("totalvolume", map2.get("totalvolume")));
            totalSalesPriceList.add(totalSalesPriceMap);
        }

        model.addAttribute("totalList", totalList);
        model.addAttribute("totalSalesPriceList", totalSalesPriceList);
    }

}
