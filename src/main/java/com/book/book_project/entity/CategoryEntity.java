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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryseq;

    @Column(name = "categorynumber", length = 200, nullable = false)
    private String categorynumber;

    //분류명
    @Column(name = "categoryname", length = 200, nullable = false)
    private String categoryname;

    @Column(name = "depth", nullable = false)
    private int depth;

    @Column(name = "parent", nullable = false)
    private String parent;
}