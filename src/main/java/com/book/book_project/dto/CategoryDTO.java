package com.book.book_project.dto;

import com.book.book_project.entity.CategoryEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private int categoryseq;
    private String categorynumber;   //분류번호
    private String categoryname;   //분류명
    private int depth;
    private String parent;

    //Entity -> DTO 이동
    public CategoryDTO (CategoryEntity categoryEntity ) {
        this.categoryseq = categoryEntity.getCategoryseq();
        this.categorynumber = categoryEntity.getCategorynumber();
        this.categoryname = categoryEntity.getCategoryname();
        this.depth=categoryEntity.getDepth();
        this.parent=categoryEntity.getParent();
    }

    //DTO -> Entity 이동
    public CategoryEntity dtoToEntity(CategoryDTO dto) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryseq(dto.getCategoryseq())
                .categorynumber(dto.getCategorynumber())
                .categoryname(dto.getCategoryname())
                .depth(dto.getDepth())
                .parent(dto.getParent())
                .build();

        return categoryEntity;
    }
}


