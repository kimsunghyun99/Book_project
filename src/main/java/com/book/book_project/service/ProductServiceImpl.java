package com.book.book_project.service;

import com.book.book_project.dto.FavoritesDTO;
import com.book.book_project.dto.ProductDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import com.book.book_project.entity.repository.MemberRepository;
import com.book.book_project.entity.repository.ProductRepository;
import com.book.book_project.entity.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    //게시물 목록 보기
    @Override
    public Page<ReviewEntity> list(int pageNum, int postNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, postNum, Sort.by(Sort.Direction.DESC, "reviewseq"));
        return reviewRepository.findAll(pageRequest);
    }

    //책 내용 보기
    //
    @Override
    public ProductDTO view(int bookid) throws Exception {
        return productRepository.findById(bookid).map(view -> new ProductDTO(view)).orElse(null);
    }










}
