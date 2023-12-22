package com.book.book_project.service;

import com.book.book_project.dto.CartDTO;
import com.book.book_project.dto.ProductDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import com.book.book_project.entity.repository.ProductRepository;
import com.book.book_project.entity.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    //게시물 목록 보기
    @Override
    public Page<ProductEntity> list(int pageNum, int postNum, String keyword) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, postNum, Sort.by(Sort.Direction.DESC,"bookid"));
        return productRepository.findByBookidContainingOrBooknameContainingOrCategorynumberContainingOrPublisher
                (keyword,keyword,keyword, keyword, pageRequest);
    }
//    댓글 목록 보기
//    @Override
//    public Page<ReviewEntity> list(int pageNum, int postNum) throws Exception {
//        PageRequest pageRequest = PageRequest.of(pageNum - 1, postNum, Sort.by(Sort.Direction.DESC, "reviewseq"));
//        return reviewRepository.findAll(pageRequest);
//    }


    //책 내용 보기
    //
    @Override
    public ProductDTO view(String bookid) throws Exception {
        return productRepository.findById(bookid).map(view -> new ProductDTO(view)).orElse(null);
    }



// 책 정보 가져오기
//    @Override
//    public ProductDTO productAll throws Exception {
//        return productRepository.getBookid;
//    }







}
