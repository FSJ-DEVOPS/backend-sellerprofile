package com.shopverse.sellerprofile.dto;

public class OrderItems
{
    String productId;
    String sellerId;
    String sellerEmail;
    String title;
    String productImage;
    int quantity;
    double price;
    String productStatus;

    public OrderItems() {
        // Default constructor for Jackson
    }

    public OrderItems(String productId, String sellerId, String title, String productImage, int quantity, double price, String productStatus) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.title = title;
        this.productImage = productImage;
        this.quantity = quantity;
        this.price = price;
        this.productStatus = productStatus;
    }

    // Keep the old constructor for backward compatibility
    public OrderItems(String productId, String sellerId, String title, String productImage, int quantity, double price) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.title = title;
        this.productImage = productImage;
        this.quantity = quantity;
        this.price = price;
        this.productStatus = "Processing"; // Default status
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }
}