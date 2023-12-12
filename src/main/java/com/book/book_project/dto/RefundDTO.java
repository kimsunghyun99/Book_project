package com.book.book_project.dto;


import com.book.book_project.entity.PurchaseInfoEntity;
import com.book.book_project.entity.RefundEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefundDTO {
    private int refundSeq;
    private PurchaseInfoEntity purchaseinfoseq;
    private String refundreason;

    public RefundDTO(RefundEntity refundEntity){
        this.refundSeq=refundEntity.getRefundseq();
        this.purchaseinfoseq=refundEntity.getPurchaseinfoseq();
        this.refundreason=refundEntity.getRefundreason();
    }

    public RefundEntity dtoToEntity(RefundDTO dto){

        return RefundEntity.builder()
                .refundseq(dto.getRefundSeq())
                .purchaseinfoseq(dto.getPurchaseinfoseq())
                .refundreason(dto.getRefundreason())
                .build();
    }

}
