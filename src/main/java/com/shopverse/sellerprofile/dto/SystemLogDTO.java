package com.shopverse.sellerprofile.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemLogDTO {
    private LocalDateTime timestamp;
    private String type;
    private String message;
    private String role;
    private String roleId;
}
