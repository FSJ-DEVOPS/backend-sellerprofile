package com.shopverse.sellerprofile.dto;

import java.util.List;

public class OrderDTO {
    String orderId;
    String userId;
    List<OrderItems> products;
    double totalPrice;
    String orderStatus;
    String address;
    String orderDate;

    public OrderDTO() {
        // Default constructor for Jackson
    }

    public OrderDTO(String orderId, String userId, List<OrderItems> products, double totalPrice, String orderStatus,String address,String orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.products = products;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.address = address;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderItems> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItems> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}