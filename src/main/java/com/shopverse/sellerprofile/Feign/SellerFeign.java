package com.shopverse.sellerprofile.Feign;

import com.shopverse.sellerprofile.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopverse.sellerprofile.dto.Sellerdto;

@FeignClient(name = "userauth", configuration = FeignConfig.class)
public interface SellerFeign {
    @GetMapping("/api/auth/user/{email}")
    Sellerdto getUserByEmail(@PathVariable String email);
}
