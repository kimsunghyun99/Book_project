package com.book.book_project.service;

import com.book.book_project.dto.DeliverAddrDTO;
import com.book.book_project.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryService {

    // 회원가입 시 주소정보 추가하기
    void memberaddrInfoRegistry(DeliverAddrDTO deliverAddr);


    // 주소정보 불러오기
    public DeliverAddrDTO list(String userid);
}
