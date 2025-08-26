package com.shopverse.sellerprofile.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class Seller {
    @Id
    private String sellerId;
    private String storeName;
    private String sellerName;
    @Indexed(unique = true)
    private String sellerEmail;
    private String sellerPhone;
    private String sellerPAN;
    private String uniqueId;
    private String registerNumber;
    private String verifiedProof;
    private String sellerPhoto;
    private String businessType;
    private String address;
    private String country;
    private String state;
    private String city;
    private String postalCode;
}