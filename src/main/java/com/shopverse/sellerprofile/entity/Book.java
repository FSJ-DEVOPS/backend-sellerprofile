package com.shopverse.sellerprofile.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Document(collection = "books")
public class Book {
    @Id
    private String bookId;
    private String sellerId;
    private String title;
    private double costPrice; // Internal use only - not exposed to customers
    private double sellingPrice; // Current selling price
    private double mrp; // Maximum Retail Price - shown to customers for discount calculation
    private String isbn;
    private String author;
    private List<String> format;
    private int pages;
    private String publisher;
    private String publishedDate;
    private List<String> language;
    private double weight;
    private int quantity;
    private String description;
    private Boolean availability;
    private List<String> genre;
    
    // Support for multiple images (new field)
    private List<String> bookImages;
    
    // Keep single image field for backward compatibility
    @Deprecated
    private String bookImage;
    
    private double bookHeight;
    private double bookWidth;
}

