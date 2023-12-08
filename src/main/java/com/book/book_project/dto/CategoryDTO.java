package com.book.book_project.dto;

import com.book.book_project.entity.CategoryEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private int category_seq;   //분류번호
    private String category_name;   //분류명

    //Entity -> DTO 이동
    public CategoryDTO (CategoryEntity categoryEntity ) {
        this.category_seq = categoryEntity.getCategory_seq();
        this.category_name = categoryEntity.getCategory_name();
    }

    //DTO -> Entity 이동
    public CategoryEntity dtoToEntity(CategoryDTO dto) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .category_seq(dto.getCategory_seq())
                .category_name(dto.getCategory_name())
                .build();

        return categoryEntity;
    }
}


