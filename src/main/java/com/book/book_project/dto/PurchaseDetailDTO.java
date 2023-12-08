package com.book.book_project.dto;

import com.book.book_project.entity.PurchaseDetailEntity;
import com.book.book_project.entity.PurchaseStatusEntity;
import com.book.book_project.entity.UnMemberEntity;
import lombok.*;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDetailDTO {
    private int purchasedetailseq;
    private ProductEntity bookid;
    private UnMemberEntity unMembertelno;
    private PurchaseStatusEntity statusseq;
    private PurchaseInfoEntity purchaseseq;
    private String deliverystatus;
    private Timestamp purchasedate;
    private int volume;
    private int totalprice;

    public PurchaseDetailDTO(PurchaseDetailEntity purchaseDetailEntity){
        this.purchasedetailseq=purchaseDetailEntity.getPurchasedetailseq();
        this.bookid=purchaseDetailEntity.getBookid();
        this.unMembertelno=purchaseDetailEntity.getUnmembertelno();
        this.statusseq=purchaseDetailEntity.getStatusseq();
        this.purchaseseq=purchaseDetailEntity.getPurchaseseq();
        this.deliverystatus=purchaseDetailEntity.getDeliverystatus();
        this.purchasedate=purchaseDetailEntity.getPurchasedate();
        this.volume=purchaseDetailEntity.getPurchasedetailseq();
        this.totalprice=purchaseDetailEntity.getPurchasedetailseq();
    }

    public PurchaseDetailEntity purchaseDetailEntity(PurchaseDetailDTO dto){

        return PurchaseDetailEntity.builder()
                .purchasedetailseq(dto.getPurchasedetailseq())
                .bookid(dto.getBookid())
                .unmembertelno(dto.getUnMembertelno())
                .statusseq(dto.getStatusseq())
                .purchaseseq(dto.getPurchaseseq())
                .deliverystatus(dto.getDeliverystatus())
                .purchasedate(dto.getPurchasedate())
                .volume(dto.getVolume())
                .totalprice(dto.getTotalprice())
                .build();
    }
    
}
