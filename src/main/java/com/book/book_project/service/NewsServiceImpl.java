package com.book.book_project.service;

import com.book.book_project.dto.NewsDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    @Override
    public List<NewsDTO> crawlNews() {
        List<NewsDTO> newsDTOList = new ArrayList<>();
        String url = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105";

        try {
            Document doc = Jsoup.connect(url).get();
            Elements newsHeadlines = doc.select(".cluster_text a");

            for (Element headline : newsHeadlines) {
                String title = headline.text();
                String link = headline.attr("href");

                NewsDTO newsDto = new NewsDTO(title, link);
                newsDTOList.add(newsDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsDTOList;
    }
}
