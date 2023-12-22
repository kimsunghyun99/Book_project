package com.book.book_project.dto;

import lombok.Getter;

@Getter
public class NewsDTO {
    private String title;
    private String url;

    public NewsDTO(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
