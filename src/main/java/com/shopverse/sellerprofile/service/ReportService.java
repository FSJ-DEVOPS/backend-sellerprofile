package com.shopverse.sellerprofile.service;

import com.shopverse.sellerprofile.dto.ReportDTO;

public interface ReportService {
    ReportDTO generateReport(String sellerId);
}