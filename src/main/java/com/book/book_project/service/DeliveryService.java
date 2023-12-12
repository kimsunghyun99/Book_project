package com.book.book_project.service;

import com.book.book_project.dto.DeliverAddrDTO;
import com.book.book_project.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryService {


    void memberaddrInfoRegistry(DeliverAddrDTO deliverAddr);
}
