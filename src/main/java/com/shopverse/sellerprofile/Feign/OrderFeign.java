package com.shopverse.sellerprofile.Feign;

import com.shopverse.sellerprofile.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="orderservice")
public interface OrderFeign {
    @GetMapping("api/order/seller/{sellerId}")
    OrderDTO getOrderBySellerId(@PathVariable String uniqueId);

    @GetMapping("api/order/seller/{sellerId}")
    List<OrderDTO> getOrderListBySellerId(@PathVariable("sellerId") String sellerId);

}
