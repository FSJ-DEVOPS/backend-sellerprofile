package com.shopverse.sellerprofile.dto;

import java.util.List;

import com.shopverse.sellerprofile.entity.Book;

import lombok.Data;

@Data
public class ReportDTO {
    private double totalRevenue;
    private long totalBooksSold;
    private long pendingOrders;
    private long totalProducts;
    private List<ProductProfit> mostProfitableBooks;
    private List<Book> lowStock;
    private List<Book> outOfStock;
    private List<GenreSales> salesByGenre;

    @Data
    public static class ProductProfit {
        private String productName;
        private double profit;
        private long unitsSold;

        public ProductProfit(String productName, double profit, long unitsSold) {
            this.productName = productName;
            this.profit = profit;
            this.unitsSold = unitsSold;
        }
    }

    @Data
    public static class GenreSales {
        private String genre;
        private long sales;

        public GenreSales(String genre, long sales) {
            this.genre = genre;
            this.sales = sales;
        }
    }
}