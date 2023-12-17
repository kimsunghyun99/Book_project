package com.book.book_project.service;

import com.book.book_project.dto.DeliverAddrDTO;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.entity.DeliveryAddrEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeliveryService {

    // 회원가입 시 주소정보 추가하기
    void memberaddrInfoRegistry(DeliverAddrDTO deliverAddr);


    // 주소정보 불러오기
    public List<DeliveryAddrEntity> list(String userid);

    // 회원정보 시 배송지 삭제
//    public void deletedeliveraddr(int deliveryseq) throws Exception;

}
