package com.shopverse.sellerprofile.repository;

import com.shopverse.sellerprofile.entity.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends MongoRepository<Seller,String> {
    List<Seller> findBySellerEmail(String sellerEmail);
}
