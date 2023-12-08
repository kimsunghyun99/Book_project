package com.book.book_project.dto;

import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.PurchaseInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInfoDTO {

    private Long purchaseseq;
    private String purchaseaddr;
    private Long purchasezipcode;
    private String purchasedetailaddr;
    private String purchasename;
    private String purchasetelno;
    private MemberEntity userid;

 public PurchaseInfoDTO(PurchaseInfoEntity purchaseinfoEntity) {

     this.purchaseseq = purchaseinfoEntity.getPurchaseseq();
     this.purchaseaddr = purchaseinfoEntity.getPurchaseaddr();
     this.purchasezipcode = purchaseinfoEntity.getPurchasezipcode();
     this.purchasedetailaddr = purchaseinfoEntity.getPurchasedetailaddr();
     this.purchasename = purchaseinfoEntity.getPurchasename();
     this.purchasetelno = purchaseinfoEntity.getPurchasetelno();
     this.userid = purchaseinfoEntity.getUserid();

    }

    //Entity --> DTO
 public PurchaseInfoEntity dtoToEntity(PurchaseInfoDTO dto) {

     PurchaseInfoEntity entity = PurchaseInfoEntity.builder()
                .purchaseseq(dto.getPurchaseseq())
                .purchaseaddr(dto.getPurchaseaddr())
                .purchasezipcode(dto.getPurchasezipcode())
                .purchasedetailaddr(dto.getPurchasedetailaddr())
                .purchasename(dto.getPurchasename())
                .purchasetelno(dto.getPurchasetelno())
                .userid(dto.getUserid())
                .build();
        return entity;
    }

}
