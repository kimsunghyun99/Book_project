package com.book.book_project.service;

import com.book.book_project.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    List<NewsDTO> crawlNews();
}
