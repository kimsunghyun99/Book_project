package com.book.book_project.dto;

import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.ProductEntity;

import java.sql.Timestamp;

public interface ReviewInterface {
	int getReviewseq();
	ProductEntity getBookid();
	MemberEntity getUserid();
	String getReviewer();
	String getReviewcontent();
	Timestamp getReviewregdate();
}
