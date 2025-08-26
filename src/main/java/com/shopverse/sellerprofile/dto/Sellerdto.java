package com.shopverse.sellerprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sellerdto {
    private String name;
    private String phoneNumber;
    private String email;
    private String panNumber;
    private String uniqueId;
}
