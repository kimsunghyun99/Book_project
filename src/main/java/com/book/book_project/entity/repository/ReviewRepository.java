package com.book.book_project.entity.repository;

import 	com.book.book_project.dto.ReviewDTO;
import com.book.book_project.dto.ReviewInterface;
import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {




@Query(value="select * from tbl_review where bookid=:bookid order by reviewseq desc", nativeQuery = true)
	List<ReviewInterface> reviewView(@Param("bookid") String bookid);

@Query(value="select * from tbl_review where bookid=:bookid order by reviewseq desc", nativeQuery = true)
	List<ReviewDTO> reviewView1(@Param("bookid") String bookid);

	Page<ReviewEntity> findByBookid(ProductEntity bookid, PageRequest pageRequest);

	@Query(value="select count(*) from tbl_review where bookid=:bookid", nativeQuery = true)
	int countreview(@Param("bookid") String bookid);

}
