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
    @Column(name="unmembertelno", length = 15, nullable = false)
    private String unmembertelno;

    @Column(name="temppassword", length = 200, nullable = false)
    private String temppassword;
    @Column(name="unmembezipcode", length = 200, nullable = false)
    private String unmembezipcode;
    @Column(name="unmemberaddress", length = 200, nullable = false)
    private String unmemberaddress;
    @Column(name="unmemberdetailaddress", length = 200, nullable = false)
    private String unmemberdetailaddress;
    @Column(name="unmembername", length = 50, nullable = false)
    private String unmembername;

}
