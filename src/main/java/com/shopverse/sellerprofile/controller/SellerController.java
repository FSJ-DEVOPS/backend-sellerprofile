////package com.shopverse.sellerprofile.controller;
////
////import com.shopverse.sellerprofile.entity.Seller;
////import com.shopverse.sellerprofile.service.SellerService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////@CrossOrigin(origins="http://localhost:5173")
////@RestController
////@RequestMapping("/api/seller")
////public class SellerController {
////    private SellerService sellerService;
////
////    public SellerController(@Autowired SellerService sellerService) {
////        this.sellerService = sellerService;
////    }
////    @PostMapping("/add")
////    public ResponseEntity<Seller> addSeller(@RequestBody Seller seller) {
////        Seller sel=sellerService.addSeller(seller);
////        return new ResponseEntity<>(sel, HttpStatus.CREATED);
////
////    }
////    @PutMapping("/{email}")
////    public ResponseEntity<Seller> updateSeller(@PathVariable String email, @RequestBody Seller seller){
////        Seller result = sellerService.updateByEmail(email, seller);
////        return new ResponseEntity<>(result, HttpStatus.CREATED);
////
////    }
////    @GetMapping("/{email}")
////    public ResponseEntity<Seller> getSellerByEmail(@PathVariable String email) {
////        Seller seller = sellerService.getSellerByEmail(email);
////        if (seller != null) {
////            return new ResponseEntity<>(seller, HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////    }
////    @PostMapping("/register/{email}")
////    public ResponseEntity<Seller> registerBasic(@PathVariable String email) {
////        Seller seller = sellerService.registerBasicSeller(email);
////        return new ResponseEntity<>(seller, HttpStatus.CREATED);
////    }
////}
//package com.shopverse.sellerprofile.controller;
//
//import com.shopverse.sellerprofile.entity.Seller;
//import com.shopverse.sellerprofile.service.SellerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin(origins = "http://localhost:5173")
//@RestController
//@RequestMapping("/api/seller")
//public class SellerController {
//
//    private final SellerService sellerService;
//
//    @Autowired
//    public SellerController(SellerService sellerService) {
//        this.sellerService = sellerService;
//    }
//
//    // POST: /api/seller/add
//    @PostMapping("/add")
//    public ResponseEntity<Seller> addSeller(@RequestBody Seller sellerForm) {
//        Seller createdSeller = sellerService.addSeller(sellerForm);
//        return new ResponseEntity<>(createdSeller, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{email}")
//    public ResponseEntity<Seller> updateSeller(@PathVariable String email, @RequestBody Seller seller) {
//        Seller updated = sellerService.updateByEmail(email, seller);
//        return new ResponseEntity<>(updated, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{email}")
//    public ResponseEntity<Seller> getSellerByEmail(@PathVariable String email) {
//        Seller seller = sellerService.getSellerByEmail(email);
//        return new ResponseEntity<>(seller, HttpStatus.OK);
//    }
//}
package com.shopverse.sellerprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopverse.sellerprofile.entity.Seller;
import com.shopverse.sellerprofile.service.SellerService;

@RestController
@RequestMapping("/api/seller/profile")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @PutMapping("/{email}")
    public ResponseEntity<Seller> updateSeller(@PathVariable String email, @RequestBody Seller seller) {
        Seller updated = sellerService.updateByEmail(email, seller);
        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Seller> getSellerByEmail(@PathVariable String email) {
        Seller seller = sellerService.getSellerByEmail(email);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

}