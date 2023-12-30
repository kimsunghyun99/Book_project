package com.book.book_project.service;

import com.book.book_project.dto.RefundDTO;
import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.repository.PurchaseInfoRepository;
import com.book.book_project.entity.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final RefundRepository refundRepository;
    @Autowired
    private PurchaseInfoRepository purchaseInfoRepository;
    public void ExchangeRegistry(RefundDTO refundDTO) throws Exception  {

        System.out.println("서비스임플1");

        int purchaseinfonumber = refundDTO.getPurchaseinfonumber().getPurchaseinfonumber();

        System.out.println("purchaseinfonumber : " + purchaseinfonumber);

        PurchaseInfoEntity purchaseInfoEntity = purchaseInfoRepository.findById(purchaseinfonumber).orElse(null);
        refundDTO.setPurchaseinfonumber(purchaseInfoEntity);


        refundRepository.save(refundDTO.dtoToEntity(refundDTO));
        System.out.println("서비스임플2");
    }

    public void RefundRegistry(RefundDTO refundDTO) throws Exception  {

        System.out.println("서비스임플1");

        int purchaseinfonumber = refundDTO.getPurchaseinfonumber().getPurchaseinfonumber();

        System.out.println("purchaseinfonumber : " + purchaseinfonumber);

        PurchaseInfoEntity purchaseInfoEntity = purchaseInfoRepository.findById(purchaseinfonumber).orElse(null);
        refundDTO.setPurchaseinfonumber(purchaseInfoEntity);


        refundRepository.save(refundDTO.dtoToEntity(refundDTO));
        System.out.println("서비스임플2");
    }






}
