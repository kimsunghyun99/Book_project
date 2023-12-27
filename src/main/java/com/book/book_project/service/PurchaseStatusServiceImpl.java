package com.book.book_project.service;


import com.book.book_project.entity.repository.PurchaseStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseStatusServiceImpl implements PurchaseStatusService {

    private final PurchaseStatusRepository purchaseStatusRepository;



    @Override
    public String getStatusName(String statusseq) throws Exception {

        System.out.println("statusseq 형 변환 전 : " + statusseq);
        int Statusseq = Integer.parseInt(statusseq);
        System.out.println("Statusseq 형 변환 : " + Statusseq);

        return purchaseStatusRepository.getStatusName(Statusseq);
    }
}
