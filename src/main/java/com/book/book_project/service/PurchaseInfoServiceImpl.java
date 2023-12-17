package com.book.book_project.service;

import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.repository.PurchaseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseInfoServiceImpl implements PurchaseInfoService {
    private final PurchaseInfoRepository repository;


    @Override
    public List<PurchaseInfoEntity> purchaseList(int buyerseq){


        return repository.findByBuyerseq(buyerseq);
    }

}
