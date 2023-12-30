package com.book.book_project.service;

import com.book.book_project.dto.MemberDTO;
import com.book.book_project.dto.RefundDTO;
import org.springframework.stereotype.Service;


public interface RefundService {


    //교환건 추가
    public void ExchangeRegistry(RefundDTO refundDTO) throws Exception;


    //환불건 추가
    public void RefundRegistry(RefundDTO refundDTO) throws Exception;

}
