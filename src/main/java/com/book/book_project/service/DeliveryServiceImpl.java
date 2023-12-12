package com.book.book_project.service;

import com.book.book_project.dto.DeliverAddrDTO;
import com.book.book_project.entity.repository.DeliveryRepository;
import com.book.book_project.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;


    @Override
    public void memberaddrInfoRegistry(DeliverAddrDTO deliverAddr) {
        deliveryRepository.save(deliverAddr.dtoToEntity(deliverAddr));
    }
}
