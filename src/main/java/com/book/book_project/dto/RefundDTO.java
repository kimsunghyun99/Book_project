package com.book.book_project.dto;


import com.book.book_project.entity.PurchaseDetailEntity;
import com.book.book_project.entity.RefundEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefundDTO {
    private int refundSeq;
    private PurchaseDetailEntity purchasedetailseq;
    private String refundreason;

    public RefundDTO(RefundEntity refundEntity){
        this.refundSeq=refundEntity.getRefundseq();
        this.purchasedetailseq=refundEntity.getPurchasedetailseq();
        this.refundreason=refundEntity.getRefundreason();
    }

    public RefundEntity refundEntity(RefundDTO dto){

        return RefundEntity.builder()
                .refundseq(dto.getRefundSeq())
                .purchasedetailseq(dto.getPurchasedetailseq())
                .refundreason(dto.getRefundreason())
                .build();
    }

}
