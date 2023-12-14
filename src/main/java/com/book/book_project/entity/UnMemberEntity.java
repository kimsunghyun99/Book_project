package com.book.book_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="unmember")
@Table(name="tbl_unmember")
public class  UnMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unmemberseq;

    @Column(name="addr", length = 200, nullable = false)
    private String addr;

    @Column(name="zipcode", length = 50, nullable = false)
    private String zipcode;

    @Column(name="detailaddr", length = 200, nullable = false)
    private String detailaddr;

    @Column(name="receivername", length = 50, nullable = false)
    private String receivername;

    @Column(name="receivertelno", length = 15, nullable = false)
    private String receivertelno;

    @Column(name="temppassword", length = 100, nullable = false)
    private String temppassword;
}
