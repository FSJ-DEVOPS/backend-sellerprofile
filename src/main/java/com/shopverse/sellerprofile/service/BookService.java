package com.shopverse.sellerprofile.service;


import com.shopverse.sellerprofile.dto.BookPatchDTO;
import com.shopverse.sellerprofile.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    public List<Book> getBooks();
    public List<Book> getBooksBySellerId(String sellerId);
    public Book getBookById(String bookId);
    public Book addBook(Book book);
    public void deleteBookById(String bookId);
    public Book updateBookById(BookPatchDTO patchedBook);
}
