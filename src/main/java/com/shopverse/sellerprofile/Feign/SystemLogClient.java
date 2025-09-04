package com.shopverse.sellerprofile.Feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopverse.sellerprofile.dto.SystemLogDTO;

@FeignClient(name = "systemlog", url = "https://backend-systemlogs.onrender.com")
public interface SystemLogClient {

    @PostMapping("/api/adminlogs/logs")
    void saveLog(SystemLogDTO log);
}
