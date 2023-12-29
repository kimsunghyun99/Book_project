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
    private PurchaseInfoEntity purchaseinfonumber;
    private String status;
    private String refundreason;

    public RefundDTO(RefundEntity refundEntity){
        this.refundSeq=refundEntity.getRefundseq();
        this.purchaseinfonumber=refundEntity.getPurchaseinfonumber();
        this.refundreason=refundEntity.getRefundreason();
        this.status=refundEntity.getStatus();
    }

    public RefundEntity dtoToEntity(RefundDTO dto){

        return RefundEntity.builder()
                .refundseq(dto.getRefundSeq())
                .purchaseinfonumber(dto.getPurchaseinfonumber())
                .refundreason(dto.getRefundreason())
                .status(dto.getStatus())
                .build();
    }

}
