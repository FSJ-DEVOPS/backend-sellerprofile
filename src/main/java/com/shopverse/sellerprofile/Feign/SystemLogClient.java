package com.shopverse.sellerprofile.Feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopverse.sellerprofile.dto.SystemLogDTO;

@FeignClient(name = "systemlog", url = "http://localhost:8087/api/adminlogs/logs")
public interface SystemLogClient {

    @PostMapping
    void saveLog(SystemLogDTO log);
}
