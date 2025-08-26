package com.shopverse.sellerprofile.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookPatchDTO {
    private String bookId;
    private String title;
    private Integer costPrice;
    private Double sellingPrice;
    private String isbn;
    private String author;
    private List<String> format;
    private Integer pages;
    private String publisher;
    private String publishedDate;
    private List<String> language;
    private Double weight;
    private Integer quantity;
    private String description;
    private Boolean availability;
    private List<String> genre;
    private String bookImage;
    private Double bookHeight;
    private Double bookWidth;
}
