package com.book.book_project.dto;

import com.book.book_project.entity.ProductEntity;
import com.book.book_project.entity.UnMemberEntity;
import com.book.book_project.entity.UnMemberPurchaseInfoEntity;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnMemberPurchaseInfoDTO {
    private int unmemberpurchaseinfoseq;
    private Timestamp purchasedate;
    private int volume;
    private int totalprice;
    private ProductEntity bookid;
    private UnMemberEntity unmemberseq;

    public UnMemberPurchaseInfoDTO(UnMemberPurchaseInfoEntity unMemberPurchaseInfoEntity){
        this.unmemberpurchaseinfoseq=unMemberPurchaseInfoEntity.getUnmemberpurchaseinfoseq();
        this.purchasedate=unMemberPurchaseInfoEntity.getPurchasedate();
        this.volume=unMemberPurchaseInfoEntity.getVolume();
        this.totalprice=unMemberPurchaseInfoEntity.getTotalprice();
        this.bookid=unMemberPurchaseInfoEntity.getBookid();
        this.unmemberseq=unMemberPurchaseInfoEntity.getUnmemberseq();
    }

    public UnMemberPurchaseInfoEntity dtoToEntity(UnMemberPurchaseInfoDTO dto){

        return new UnMemberPurchaseInfoEntity().builder()
                .unmemberpurchaseinfoseq(dto.getUnmemberpurchaseinfoseq())
                .purchasedate(dto.getPurchasedate())
                .volume(dto.getVolume())
                .totalprice(dto.getTotalprice())
                .bookid(dto.getBookid())
                .unmemberseq(dto.getUnmemberseq())
                .build();
    }

}
