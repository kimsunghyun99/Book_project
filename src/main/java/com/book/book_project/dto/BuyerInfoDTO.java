package com.book.book_project.dto;

import com.book.book_project.entity.MemberEntity;
import com.book.book_project.entity.BuyerInfoEntity;
import com.book.book_project.entity.UnMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyerInfoDTO {

    private Long purchaseseq;
    private UnMemberEntity unmembertelno;
    private String purchaseaddr;
    private Long purchasezipcode;
    private String purchasedetailaddr;
    private String purchasename;
    private String purchasetelno;
    private MemberEntity userid;

 public BuyerInfoDTO(BuyerInfoEntity purchaseinfoEntity) {

     this.purchaseseq = purchaseinfoEntity.getPurchaseseq();
     this.unmembertelno = purchaseinfoEntity.getUnmembertelno();
     this.purchaseaddr = purchaseinfoEntity.getPurchaseaddr();
     this.purchasezipcode = purchaseinfoEntity.getPurchasezipcode();
     this.purchasedetailaddr = purchaseinfoEntity.getPurchasedetailaddr();
     this.purchasename = purchaseinfoEntity.getPurchasename();
     this.purchasetelno = purchaseinfoEntity.getPurchasetelno();
     this.userid = purchaseinfoEntity.getUserid();

    }

    //Entity --> DTO
 public BuyerInfoEntity dtoToEntity(BuyerInfoDTO dto) {

     BuyerInfoEntity entity = BuyerInfoEntity.builder()
                .purchaseseq(dto.getPurchaseseq())
                .unmembertelno(dto.getUnmembertelno())
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
