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

    public UnMemberDTO(UnMemberEntity unMemberEntity){
        this.unmembertelno=unMemberEntity.getUnmembertelno();
        this.temppassword=unMemberEntity.getTemppassword();
    }

    public UnMemberEntity dtoToEntity(UnMemberDTO dto){

        return UnMemberEntity.builder()
                .unmembertelno(dto.getUnmembertelno())
                .temppassword(dto.getTemppassword())
                .build();
    }
}
