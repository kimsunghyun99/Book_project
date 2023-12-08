package com.book.book_project.dto;

import com.book.book_project.entity.CategoryEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private int categoryseq;   //분류번호
    private String categoryname;   //분류명

    //Entity -> DTO 이동
    public CategoryDTO (CategoryEntity categoryEntity ) {
        this.categoryseq = categoryEntity.getCategoryseq();
        this.categoryname = categoryEntity.getCategoryname();
    }

    //DTO -> Entity 이동
    public CategoryEntity dtoToEntity(CategoryDTO dto) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryseq(dto.getCategoryseq())
                .categoryname(dto.getCategoryname())
                .build();

        return categoryEntity;
    }
}


