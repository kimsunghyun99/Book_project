package com.book.book_project.service;

import org.springframework.stereotype.Service;

@Service
public interface PurchaseStatusService {



    //주문상태 가져오기
    public String getStatusName(String statusseq) throws Exception;





}
