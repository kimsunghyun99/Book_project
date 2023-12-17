package com.book.book_project.dto;

import java.sql.Timestamp;

import java.sql.Timestamp;

public interface ReviewInterface {
	int getReviewseq();
	String getBookid();
	String getUserid();
	String getReviewer();
	String getReviewcontent();
	Timestamp getReviewregdate();
}
