package com.book.book_project.dto;

import com.book.book_project.entity.UnMemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnMemberDTO {
    private int unmemberseq;
    private String addr;
    private String zipcode;
    private String detailaddr;
    private String receivername;
    private String receivertelno;
    private String temppassword;



    public UnMemberDTO(UnMemberEntity unMemberEntity){
        this.unmemberseq=unMemberEntity.getUnmemberseq();
        this.addr=unMemberEntity.getAddr();
        this.zipcode=unMemberEntity.getZipcode();
        this.detailaddr=unMemberEntity.getDetailaddr();
        this.receivername=unMemberEntity.getReceivername();
        this.receivertelno=unMemberEntity.getReceivertelno();
        this.temppassword=unMemberEntity.getTemppassword();
    }

    public UnMemberEntity dtoToEntity(UnMemberDTO dto){

        return UnMemberEntity.builder()
                .unmemberseq(dto.getUnmemberseq())
                .addr(dto.getAddr())
                .zipcode(dto.getZipcode())
                .detailaddr(dto.getDetailaddr())
                .receivername(dto.getReceivername())
                .receivertelno(dto.getReceivertelno())
                .temppassword(dto.getTemppassword())
                .build();
    }
}
