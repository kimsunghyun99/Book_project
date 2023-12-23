package com.book.book_project.entity.repository;

import com.book.book_project.entity.FavoritesEntity;
import com.book.book_project.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FavoritesRepository {


    public Optional<FavoritesEntity> findById(String bookid);

}
