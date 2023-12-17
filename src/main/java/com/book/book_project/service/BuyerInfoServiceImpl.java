package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.repository.BuyerInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerInfoServiceImpl implements BuyerInfoService {

   private final BuyerInfoRepository repository;
//    @Override
//    public List<BuyerInfoEntity> buyerInfo(MemberEntity userid) {
//        return repository.findByUserid(userid);
//    }
}
