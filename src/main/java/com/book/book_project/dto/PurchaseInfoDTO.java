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
    private int purchaseinfoseq;
    private ProductEntity bookid;
    private PurchaseStatusEntity statusseq;
    private String deliverystatus;
    private Timestamp purchasedate;
    private int volume;
    private int totalprice;

    public PurchaseInfoDTO(PurchaseInfoEntity purchaseInfoEntity){
        this.purchaseinfoseq=purchaseInfoEntity.getPurchaseinfoseq();
        this.bookid=purchaseInfoEntity.getBookid();
        this.statusseq=purchaseInfoEntity.getStatusseq();
        this.deliverystatus=purchaseInfoEntity.getDeliverystatus();
        this.purchasedate=purchaseInfoEntity.getPurchasedate();
        this.volume=purchaseInfoEntity.getVolume();
        this.totalprice=purchaseInfoEntity.getTotalprice();
    }

    public PurchaseInfoEntity dtoToEntity(PurchaseInfoDTO dto){

        return PurchaseInfoEntity.builder()
                .purchaseinfoseq(dto.getPurchaseinfoseq())
                .bookid(dto.getBookid())
                .statusseq(dto.getStatusseq())
                .deliverystatus(dto.getDeliverystatus())
                .purchasedate(dto.getPurchasedate())
                .volume(dto.getVolume())
                .totalprice(dto.getTotalprice())
                .build();
    }

}
