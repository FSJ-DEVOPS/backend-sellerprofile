package com.shopverse.sellerprofile.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopverse.sellerprofile.Feign.OrderFeign;
import com.shopverse.sellerprofile.dto.OrderDTO;
import com.shopverse.sellerprofile.dto.OrderItems;
import com.shopverse.sellerprofile.dto.ReportDTO;
import com.shopverse.sellerprofile.entity.Book;
import com.shopverse.sellerprofile.repository.BookRepository;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderFeign orderFeign; // Your existing Feign client

    @Override
    public ReportDTO generateReport(String sellerId) {
        // System.out.println("Generating report for seller: " + sellerId);
        
        List<Book> books = bookRepository.findBySellerId(sellerId).orElse(List.of());
        // System.out.println("Found " + books.size() + " books for seller");

        // Use the NEW, safe method to get the list of orders
        List<OrderDTO> orders;
        try {
            orders = orderFeign.getOrderListBySellerId(sellerId);
            // System.out.println("Found " + orders.size() + " orders for seller");

            if (!orders.isEmpty()) {
                // System.out.println("First order details:");
                OrderDTO firstOrder = orders.get(0);
                // System.out.println("Order ID: " + firstOrder.getOrderId());
                // System.out.println("Products count: " + (firstOrder.getProducts() != null ? firstOrder.getProducts().size() : "null"));
                if (firstOrder.getProducts() != null && !firstOrder.getProducts().isEmpty()) {
                    OrderItems firstProduct = firstOrder.getProducts().get(0);
                    // System.out.println("First product status: " + firstProduct.getProductStatus());
                    // System.out.println("First product ID: " + firstProduct.getProductId());
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching orders: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

        ReportDTO report = new ReportDTO();

        report.setTotalProducts(books.size());

        // Calculate total revenue and books sold from all orders
        double totalRevenue = orders.stream().mapToDouble(OrderDTO::getTotalPrice).sum();
        long totalBooksSold = orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .mapToLong(p -> p.getQuantity())
                .sum();

        report.setTotalRevenue(totalRevenue);
        report.setTotalBooksSold(totalBooksSold);
        
        // Count orders that have at least one product with "Processing" status (not individual products)
        // System.out.println("Starting pending orders calculation...");
        // System.out.println("Total orders to check: " + orders.size());
        
        long pendingOrdersCount = 0;
        for (OrderDTO order : orders) {
            // System.out.println("Checking order: " + order.getOrderId());
            if (order.getProducts() != null) {
                // System.out.println("Products count: " + order.getProducts().size());
                for (OrderItems product : order.getProducts()) {
                    // System.out.println("Product ID: " + product.getProductId());
                    // System.out.println("Product Status: '" + product.getProductStatus() + "'");
                }
                
                boolean hasProcessingProduct = order.getProducts().stream()
                        .anyMatch(product -> {
                            String status = product.getProductStatus();
                            // System.out.println("   Checking status: '" + status + "' == 'Processing' ? " + "Processing".equals(status));
                            return "Processing".equals(status);
                        });
                
                if (hasProcessingProduct) {
                    pendingOrdersCount++;
                    // System.out.println("Order " + order.getOrderId() + " has processing products - counted as pending");
                } else {
                    // System.out.println("Order " + order.getOrderId() + " has no processing products");
                }
            } else {
                System.out.println("No products found in this order");
            }
        }
        
        // System.out.println("Final pending orders count: " + pendingOrdersCount);
        report.setPendingOrders(pendingOrdersCount);
        // Calculate most profitable books
        List<ReportDTO.ProductProfit> profitableBooks = books.stream().map(book -> {
                    long unitsSold = orders.stream()
                            .flatMap(o -> o.getProducts().stream())
                            .filter(p -> p.getProductId().equals(book.getBookId()))
                            .mapToLong(p -> p.getQuantity())
                            .sum();
                    double profit = (book.getSellingPrice() - book.getCostPrice()) * unitsSold;
                    return new ReportDTO.ProductProfit(book.getTitle(), profit, unitsSold);
                }).sorted((p1, p2) -> Double.compare(p2.getProfit(), p1.getProfit()))
                .limit(5)
                .collect(Collectors.toList());

        report.setMostProfitableBooks(profitableBooks);

        // Get inventory stats
        report.setLowStock(books.stream().filter(b -> b.getQuantity() > 0 && b.getQuantity() < 10).collect(Collectors.toList()));
        report.setOutOfStock(books.stream().filter(b -> b.getQuantity() == 0).collect(Collectors.toList()));

        // Calculate sales by genre
        List<ReportDTO.GenreSales> genreSales = books.stream()
                .flatMap(book -> book.getGenre().stream())
                .distinct()
                .map(genre -> {
                    long sales = orders.stream()
                            .flatMap(o -> o.getProducts().stream())
                            .filter(p -> books.stream().anyMatch(b -> b.getBookId().equals(p.getProductId()) && b.getGenre().contains(genre)))
                            .mapToLong(p -> p.getQuantity())
                            .sum();
                    return new ReportDTO.GenreSales(genre, sales);
                }).sorted((g1, g2) -> Long.compare(g2.getSales(), g1.getSales()))
                .limit(5)
                .collect(Collectors.toList());

        report.setSalesByGenre(genreSales);

        return report;
    }
}