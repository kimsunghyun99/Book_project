package com.book.book_project.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class CartEntityID implements Serializable {
    private String userid;
    private String bookid;
}
