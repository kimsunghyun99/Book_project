package com.book.book_project.service;


import com.book.book_project.entity.repository.PurchaseStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseStatusServiceImpl implements PurchaseStatusService {

    private PurchaseStatusRepository purchaseStatusRepository;



    @Override
    public String getStatusName(String statusseq) throws Exception {

        int Statusseq = Integer.parseInt(statusseq);
        return purchaseStatusRepository.getStatusName(Statusseq);
    }
}
