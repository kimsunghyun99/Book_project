package com.book.book_project.dto;

import com.book.book_project.entity.UnMemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnMemberDTO {
    private String unmembertelno;
    private String temppassword;
    private String unmemberzipcode;
    private String unmemberaddress;
    private String unmemberdetailaddress;
    private String buyer;

    public UnMemberDTO(UnMemberEntity unMemberEntity){
        this.unmembertelno=unMemberEntity.getUnmembertelno();
        this.temppassword=unMemberEntity.getTemppassword();
        this.unmemberzipcode=unMemberEntity.getUnmembezipcode();
        this.unmemberaddress=unMemberEntity.getUnmemberaddress();
        this.unmemberdetailaddress=unMemberEntity.getUnmemberdetailaddress();
        this.buyer=unMemberEntity.getBuyer();
    }

    public UnMemberEntity dtoToEntity(UnMemberDTO dto){

        return UnMemberEntity.builder()
                .unmembertelno(dto.getUnmembertelno())
                .temppassword(dto.getTemppassword())
                .unmembezipcode(dto.getUnmemberzipcode())
                .unmemberaddress(dto.getUnmemberaddress())
                .unmemberdetailaddress(dto.getUnmemberdetailaddress())
                .build();
    }
}
