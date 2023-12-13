package com.book.book_project.service;

import com.book.book_project.dto.FavoritesDTO;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.dto.ProductDTO;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.repository.FavoritesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class FavoritesServiceImpl implements FavoritesService{
    //찜 정보 가져 오기
    private final FavoritesRepository favoritesRepository;
    @Override
    public FavoritesDTO favoritesInfo(String userid) throws Exception{
        return favoritesRepository.findById(userid).map(favorites -> new FavoritesDTO(favorites).get();
    }
}
