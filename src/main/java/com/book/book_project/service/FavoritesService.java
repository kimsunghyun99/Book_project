package com.book.book_project.service;

import com.book.book_project.dto.FavoritesDTO;

public interface FavoritesService {
    //찜 정보 가져 오기
    public FavoritesDTO favoritesInfo(String userid) throws Exception;
}
