package com.book.book_project.service;

import com.book.book_project.dto.FavoritesDTO;
import com.book.book_project.entity.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final FavoriteRepository likeRepository;

}
