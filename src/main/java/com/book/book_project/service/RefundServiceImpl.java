package com.book.book_project.service;

import com.book.book_project.dto.RefundDTO;
import com.book.book_project.entity.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final RefundRepository refundRepository;
    public void ExchangeRegistry(RefundDTO refundDTO)  {

        refundRepository.save(refundDTO.dtoToEntity(refundDTO));
    }




}
