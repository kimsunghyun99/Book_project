package com.book.book_project.dto;

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

    private Long purchase_seq;
    private String purchase_addr;
    private Long purchase_zipcode;
    private String purchase_detail_addr;
    private String purchase_name;
    private String purchase_telno;
    private String userId;

 public PurchaseInfoDTO(PurchaseInfoEntity purchaseinfoEntity) {

     this.purchase_seq = purchaseinfoEntity.getPurchase_seq();
     this.purchase_addr = purchaseinfoEntity.getPurchase_addr();
     this.purchase_zipcode = purchaseinfoEntity.getPurchase_zipcode();
     this.purchase_detail_addr = purchaseinfoEntity.getPurchase_detail_addr();
     this.purchase_name = purchaseinfoEntity.getPurchase_name()
     this.purchase_telno = purchaseinfoEntity.getPurchase_telno();
     this.userId = purchaseinfoEntity.getUserid();

    }

    //Entity --> DTO
 public PurchaseInfoEntity dtoToEntity(PurchaseInfoDTO dto) {

     PurchaseInfoEntity entity = PurchaseInfoEntity.builder()
                .purchase_seq(dto.getPurchase_seq())
                .purchase_addr(dto.getPurchase_addr())
                .purchase_zipcode(dto.getPurchase_zipcode())
                .purchase_detail_addr(dto.getUserId())
                .purchase_name(dto.getPurchase_name())
                .purchase_telno(dto.getPurchase_telno())
                .userId(dto.getUserId())
                .build();
        return entity;
    }

}
