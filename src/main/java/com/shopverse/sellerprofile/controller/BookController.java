package com.shopverse.sellerprofile.controller;


import com.shopverse.sellerprofile.dto.BookPatchDTO;
import com.shopverse.sellerprofile.entity.Book;
import com.shopverse.sellerprofile.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @GetMapping("/{sellerId}")
    public ResponseEntity<List<Book>> getBooksBySellerId(@PathVariable String sellerId) {
        List<Book> _sellerBooks = bookServiceImpl.getBooksBySellerId(sellerId);
        return  new ResponseEntity<>(_sellerBooks, HttpStatus.OK);
    }

    @GetMapping("/id/{bookId}")
    public ResponseEntity<Book> getProductByTitle(@PathVariable String bookId) {
        Book book = bookServiceImpl.getBookById(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addProduct(@RequestBody Book book) {
        if(book != null) {
            bookServiceImpl.addBook(book);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String bookId) {
        if(bookId != null) {
            bookServiceImpl.deleteBookById(bookId);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/update")
    public ResponseEntity<BookPatchDTO> updateProductById(@RequestBody BookPatchDTO patchedBook) {
        bookServiceImpl.updateBookById(patchedBook);
        return new ResponseEntity<>(patchedBook, HttpStatus.OK);
    }
}
