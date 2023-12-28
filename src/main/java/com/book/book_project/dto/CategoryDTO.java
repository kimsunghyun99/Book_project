package com.book.book_project.dto;

import com.book.book_project.entity.CategoryEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private int categorynumber;   //분류번호
    private String categoryname;   //분류명
    private String depth1;
    private String depth2;
    private String depth3;
    private String depth4;

    //Entity -> DTO 이동
    public CategoryDTO (CategoryEntity categoryEntity ) {
        this.categorynumber = categoryEntity.getCategorynumber();
        this.categoryname = categoryEntity.getCategoryname();
        this.depth1=categoryEntity.getDepth1();
        this.depth2=categoryEntity.getDepth2();
        this.depth3=categoryEntity.getDepth3();
        this.depth4=categoryEntity.getDepth4();
    }

    //DTO -> Entity 이동
    public CategoryEntity dtoToEntity(CategoryDTO dto) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categorynumber(dto.getCategorynumber())
                .categoryname(dto.getCategoryname())
                .depth1(dto.getDepth1())
                .depth2(dto.getDepth2())
                .depth3(dto.getDepth3())
                .depth4(dto.getDepth4())
                .build();

        return categoryEntity;
    }
}


