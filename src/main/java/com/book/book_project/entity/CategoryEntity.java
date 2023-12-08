package com.book.book_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="category")
@Table(name="tbl_category")
public class CategoryEntity {

    //분류번호
    @Id
    @Column(name = "categoryseq", nullable = false)
    private int categoryseq;

    //분류명
    @Column(name = "categoryname", length = 200, nullable = false)
    private String categoryname;
}
