package com.book.book_project.service;

import com.book.book_project.dto.DeliverAddrDTO;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.repository.DeliveryRepository;
import com.book.book_project.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    //회원가입 시 주소정보 넣기
    @Override
    public void memberaddrInfoRegistry(DeliverAddrDTO deliverAddr) {
        deliveryRepository.save(deliverAddr.dtoToEntity(deliverAddr));

    }
        // 주소 정보 가져오기
        @Override
        public DeliverAddrDTO list(String userid) {
            return (DeliverAddrDTO) deliveryRepository.findDeliveryAddrEntitiesBy(userid);
        }



}
