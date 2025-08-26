package com.shopverse.sellerprofile.controller;

import com.shopverse.sellerprofile.dto.ReportDTO;
import com.shopverse.sellerprofile.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/{sellerId}")
    public ResponseEntity<ReportDTO> getReport(@PathVariable String sellerId) {
        return ResponseEntity.ok(reportService.generateReport(sellerId));
    }
}