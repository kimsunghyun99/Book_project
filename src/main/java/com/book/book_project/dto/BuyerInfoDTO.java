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

    private int buyerseq;
    private MemberEntity userid;
    private UnMemberEntity unmembertelno;
    private int receiverzipcode;
    private String receiveraddr;
    private String receiverdetailaddr;
    private String receivername;
    private String receivertelno;

 public BuyerInfoDTO(BuyerInfoEntity purchaseinfoEntity) {

     this.buyerseq = purchaseinfoEntity.getBuyerseq();
     this.unmembertelno = purchaseinfoEntity.getUnmembertelno();
     this.receiverdetailaddr = purchaseinfoEntity.getReceiverdetailaddr();
     this.receiverzipcode = purchaseinfoEntity.getReceiverzipcode();
     this.receiveraddr = purchaseinfoEntity.getReceiveraddr();
     this.receivername = purchaseinfoEntity.getReceivername();
     this.receivertelno = purchaseinfoEntity.getReceivertelno();
     this.userid = purchaseinfoEntity.getUserid();

    }

    //Entity --> DTO
 public BuyerInfoEntity dtoToEntity(BuyerInfoDTO dto) {

     BuyerInfoEntity entity = BuyerInfoEntity.builder()
                .buyerseq(dto.getBuyerseq())
                .unmembertelno(dto.getUnmembertelno())
                .receiverdetailaddr(dto.getReceiverdetailaddr())
                .receiverzipcode(dto.getReceiverzipcode())
                .receiveraddr(dto.getReceiveraddr())
                .receivername(dto.getReceivername())
                .receivertelno(dto.getReceivertelno())
                .userid(dto.getUserid())
                .build();
        return entity;
    }

}
