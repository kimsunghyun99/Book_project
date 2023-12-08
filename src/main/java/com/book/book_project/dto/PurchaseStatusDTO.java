package com.book.book_project.dto;

import com.book.book_project.entity.PurchaseStatusEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseStatusDTO {
    private int statusseq;
    private String statusname;

    public PurchaseStatusDTO(PurchaseStatusEntity purchaseStatusEntity){
        this.statusseq=purchaseStatusEntity.getStatusseq();
        this.statusname=purchaseStatusEntity.getStatusname();
    }

    public PurchaseStatusEntity purchaseStatusEntity(PurchaseStatusDTO dto){

        return PurchaseStatusEntity.builder()
                .statusseq(dto.getStatusseq())
                .statusname(dto.getStatusname())
                .build();
    }
}
