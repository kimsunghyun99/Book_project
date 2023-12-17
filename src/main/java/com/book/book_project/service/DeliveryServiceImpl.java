package com.book.book_project.service;

import com.book.book_project.dto.DeliverAddrDTO;
import com.book.book_project.dto.MemberDTO;
import com.book.book_project.entity.DeliveryAddrEntity;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.repository.DeliveryRepository;
import com.book.book_project.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        public List<DeliveryAddrEntity> list(String userid) {
            return deliveryRepository.findDeliveryAddrEntitiesBy(userid);
        }

    // 회원정보 수정 시 , 주소 삭제
//    @Override
//    public void deletedeliveraddr(int deliveryseq) throws Exception {
//        DeliveryAddrEntity deliveryAddrEntity = DeliveryRepository.findById(deliveryseq).get();
//        DeliveryRepository.delete(deliveryAddrEntity);
//    }



}
