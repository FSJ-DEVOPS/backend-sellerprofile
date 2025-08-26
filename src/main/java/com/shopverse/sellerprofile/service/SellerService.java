package com.shopverse.sellerprofile.service;

import com.shopverse.sellerprofile.entity.Seller;
import org.springframework.stereotype.Service;

@Service
public interface SellerService {
    Seller updateByEmail(String sellerEmail, Seller seller);
    Seller getSellerByEmail(String sellerEmail);

}
