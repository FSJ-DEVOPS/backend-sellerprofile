package com.shopverse.sellerprofile.Feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopverse.sellerprofile.dto.SystemLogDTO;

@FeignClient(name = "systemlog")
public interface SystemLogClient {

    @PostMapping
    void saveLog(SystemLogDTO log);
}
