package com.shopverse.sellerprofile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopverse.sellerprofile.Feign.SellerFeign;
import com.shopverse.sellerprofile.dto.Sellerdto;
import com.shopverse.sellerprofile.entity.Seller;
import com.shopverse.sellerprofile.repository.SellerRepository;
import com.shopverse.sellerprofile.utils.SellerNotFoundException;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final SellerFeign sellerFeign;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, SellerFeign sellerFeign) {
        this.sellerRepository = sellerRepository;
        this.sellerFeign = sellerFeign;
    }

    @Override
    public Seller getSellerByEmail(String sellerEmail) {
        // System.out.println("Searching for seller with email: " + sellerEmail);
        List<Seller> existingSellers = sellerRepository.findBySellerEmail(sellerEmail);
        // System.out.println("Found " + existingSellers.size() + " sellers");

        if (!existingSellers.isEmpty()) {
            // System.out.println("Returning existing seller");
            return existingSellers.get(0);
        }

        // System.out.println("No seller found, creating new basic profile");
        Sellerdto userDto = sellerFeign.getUserByEmail(sellerEmail);
        // System.out.println("UserAuth returned userDto with uniqueId: " + userDto.getUniqueId());
        
        Seller newSeller = new Seller();
        newSeller.setSellerEmail(userDto.getEmail());
        newSeller.setSellerName(userDto.getName());
        newSeller.setSellerPhone(userDto.getPhoneNumber());
        newSeller.setSellerPAN(userDto.getPanNumber());
        newSeller.setUniqueId(userDto.getUniqueId());

        newSeller.setSellerId(userDto.getUniqueId());

        // System.out.println("Setting uniqueId for new seller: " + userDto.getUniqueId());
        // System.out.println("Saving new seller profile to MongoDB");
        return sellerRepository.save(newSeller);
    }

    @Override
    @Transactional
    public Seller updateByEmail(String email, Seller seller) {
        // System.out.println("Updating seller profile for email: " + email);
        Seller existing = sellerRepository.findBySellerEmail(email)
                .stream().findFirst()
                .orElseThrow(() -> new SellerNotFoundException("Seller not found with email: " + email));

        // System.out.println("Found existing seller, updating fields...");
        if (seller.getStoreName() != null) existing.setStoreName(seller.getStoreName());
        if (seller.getSellerName() != null) existing.setSellerName(seller.getSellerName());
        if (seller.getSellerPhone() != null) existing.setSellerPhone(seller.getSellerPhone());
        if (seller.getRegisterNumber() != null) existing.setRegisterNumber(seller.getRegisterNumber());
        if (seller.getBusinessType() != null) existing.setBusinessType(seller.getBusinessType());
        if (seller.getAddress() != null) existing.setAddress(seller.getAddress());
        if (seller.getCountry() != null) existing.setCountry(seller.getCountry());
        if (seller.getState() != null) existing.setState(seller.getState());
        if (seller.getCity() != null) existing.setCity(seller.getCity());
        if (seller.getPostalCode() != null) existing.setPostalCode(seller.getPostalCode());
        if (seller.getSellerPAN() != null) existing.setSellerPAN(seller.getSellerPAN());

        if (seller.getVerifiedProof() != null && !seller.getVerifiedProof().isEmpty()) {
            existing.setVerifiedProof(seller.getVerifiedProof());
        }
        if (seller.getSellerPhoto() != null && !seller.getSellerPhoto().isEmpty()) {
            existing.setSellerPhoto(seller.getSellerPhoto());
        }
        // System.out.println("Saving updated seller profile to MongoDB");
        Seller saved = sellerRepository.save(existing);
        // System.out.println("Seller profile updated successfully");
        return saved;
    }
}