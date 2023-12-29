package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.MemberEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BuyerInfoService {
   List<BuyerInfoEntity> buyerInfo(MemberEntity userid);
   public BuyerInfoEntity findByBuyerseq(int buyerseq);


}