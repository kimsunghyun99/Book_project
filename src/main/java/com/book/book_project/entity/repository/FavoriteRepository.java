package com.book.book_project.entity.repository;

import com.book.book_project.entity.FavoritesEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface FavoriteRepository extends JpaRepository<FavoritesEntity, String> {



}
