package com.book.book_project.entity;

import jakarta.persistence.*;
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
    @Column(name = "categorynumber", length = 200, nullable = false)
    private int categorynumber;

    //분류명
    @Column(name = "categoryname", length = 200, nullable = false)
    private String categoryname;

    @Column(name = "parent", nullable = true)
    private String parent;
}