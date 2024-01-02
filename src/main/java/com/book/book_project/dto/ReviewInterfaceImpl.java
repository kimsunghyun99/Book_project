package com.book.book_project.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


    public class ReviewInterfaceImpl implements ReviewInterface {
        private int reviewseq;
        private String bookid;
        private String userid;
        private String reviewer;
        private String reviewcontent;
        private String reviewregdate;

        @JsonCreator
        public ReviewInterfaceImpl(@JsonProperty("reviewseq") int reviewseq,
                                   @JsonProperty("bookid") String bookid,
                                   @JsonProperty("userid") String userid,
                                   @JsonProperty("reviewer") String reviewer,
                                   @JsonProperty("reviewcontent") String reviewcontent,
                                   @JsonProperty("reviewregdate") String reviewregdate) {
            this.reviewseq = reviewseq;
            this.bookid = bookid;
            this.userid = userid;
            this.reviewer = reviewer;
            this.reviewcontent = reviewcontent;
            this.reviewregdate = reviewregdate;
        }

//        // bookid만을 파라미터로 받는 생성자
//        public ReviewInterfaceImpl(String bookid) {
//            this.bookid = bookid;
//            // 다른 필드들은 기본값이나 null로 초기화 될 수 있습니다.
//            // 예를 들어, 정수형은 0, 객체는 null로 초기화 됩니다.
//        }

        @Override
        public int getReviewseq() {
            return reviewseq;
        }

        @Override
        public String getBookid() {
            return bookid;
        }

        @Override
        public String getUserid() {
            return userid;
        }

        @Override
        public String getReviewer() {
            return reviewer;
        }

        @Override
        public String getReviewcontent() {
            return reviewcontent;
        }

        @Override
        public String getReviewregdate() {
            return reviewregdate;
        }
    }

