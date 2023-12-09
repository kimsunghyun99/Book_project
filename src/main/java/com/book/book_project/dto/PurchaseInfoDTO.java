package com.book.book_project.dto;

        import com.book.book_project.entity.*;
        import lombok.*;

        import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseInfoDTO {
    private int purchasedetailseq;
    private ProductEntity bookid;
    private PurchaseStatusEntity statusseq;
    private RefundEntity purchaseseq;
    private String deliverystatus;
    private Timestamp purchasedate;
    private int volume;
    private int totalprice;

    public PurchaseInfoDTO(PurchaseInfoEntity purchaseInfoEntity){
        this.purchasedetailseq=purchaseInfoEntity.getPurchasedetailseq();
        this.bookid=purchaseInfoEntity.getBookid();
        this.statusseq=purchaseInfoEntity.getStatusseq();
        this.purchaseseq=purchaseInfoEntity.getPurchaseseq();
        this.deliverystatus=purchaseInfoEntity.getDeliverystatus();
        this.purchasedate=purchaseInfoEntity.getPurchasedate();
        this.volume=purchaseInfoEntity.getPurchasedetailseq();
        this.totalprice=purchaseInfoEntity.getPurchasedetailseq();
    }

    public PurchaseInfoEntity dtoToEntity(PurchaseInfoDTO dto){

        return PurchaseInfoEntity.builder()
                .purchasedetailseq(dto.getPurchasedetailseq())
                .bookid(dto.getBookid())
                .statusseq(dto.getStatusseq())
                .purchaseseq(dto.getPurchaseseq())
                .deliverystatus(dto.getDeliverystatus())
                .purchasedate(dto.getPurchasedate())
                .volume(dto.getVolume())
                .totalprice(dto.getTotalprice())
                .build();
    }

}
