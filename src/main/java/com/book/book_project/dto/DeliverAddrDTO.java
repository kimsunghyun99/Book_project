package com.book.book_project.dto;

import com.book.book_project.entity.AddressEntity;
import com.book.book_project.entity.DeliveryAddrEntity;
import com.book.book_project.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliverAddrDTO {
    private String deliveryseq;
    private MemberEntity userid;
    private String addr;
    private String detailaddr;
    private int zipcode;
    private String name;
    private String memo;
    private String telno;


//Entity --> DTO

    public DeliverAddrDTO(DeliveryAddrEntity deliveryAddrEntity) {


        this.deliveryseq =deliveryAddrEntity.getDeliveryseq();
        this.userid =deliveryAddrEntity.getUserid();
        this.addr =deliveryAddrEntity.getAddr();
        this.detailaddr =deliveryAddrEntity.getDetailaddr();
        this.zipcode =deliveryAddrEntity.getZipcode();
        this.name =deliveryAddrEntity.getName();
        this.memo =deliveryAddrEntity.getMemo();
        this.telno =deliveryAddrEntity.getTelno();

    }


    //DTO --> Entity

    public DeliveryAddrEntity dtotoEntity(DeliverAddrDTO dto)  {

        DeliveryAddrEntity deliveryAddrEntity = DeliveryAddrEntity.builder()
                .deliveryseq(dto.getDeliveryseq())
                .userid(dto.getUserid())
                .addr(dto.getAddr())
                .detailaddr(dto.getDetailaddr())
                .zipcode(dto.getZipcode())
                .name(dto.getName())
                .memo(dto.getMemo())
                .telno(dto.getTelno())
                .build();
        return deliveryAddrEntity;


    }



}
