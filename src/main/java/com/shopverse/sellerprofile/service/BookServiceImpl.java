package com.shopverse.sellerprofile.service;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopverse.sellerprofile.Feign.SystemLogClient;
import com.shopverse.sellerprofile.dto.BookPatchDTO;
import com.shopverse.sellerprofile.dto.SystemLogDTO;
import com.shopverse.sellerprofile.entity.Book;
import com.shopverse.sellerprofile.repository.BookRepository;
import com.shopverse.sellerprofile.utils.BookAlreadyExistsException;
import com.shopverse.sellerprofile.utils.BookNotFoundException;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SystemLogClient systemLogClient;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksBySellerId(String sellerId) {
        Optional<List<Book>> books = bookRepository.findBySellerId(sellerId);
        return books.orElse(Collections.emptyList());
    }

    public Book getBookById(String bookId) {
        Optional<Book> _getBook = bookRepository.findBookByBookId(bookId);
        if(_getBook.isPresent()) {
            Book book = _getBook.get();
            return book;
        } else {
            throw new BookNotFoundException(bookId+" - book not found");
        }
    }

    @Override
    public Book addBook(Book book) {
        Optional<Book> _book = bookRepository.findBookByBookId(book.getBookId());
        if(_book.isPresent()) {
            throw new BookAlreadyExistsException(book.getTitle() +" - book already exists");
        } else {
            bookRepository.save(book);

            SystemLogDTO log = new SystemLogDTO();
            log.setTimestamp(LocalDateTime.now());
            log.setType("Product Update");
            log.setMessage("Seller added book with ID " + book.getBookId() +
                    " (Title: " + book.getTitle() + ")");
            log.setRole("Seller");
            log.setRoleId(book.getSellerId());
            systemLogClient.saveLog(log);
            return book;
        }
    }

    @Override
    public void deleteBookById(String bookId) {
        Optional<Book> _book = bookRepository.findBookByBookId(bookId);
        if(_book.isPresent()) {
            Book book = _book.get();
            SystemLogDTO log = new SystemLogDTO();
            log.setTimestamp(LocalDateTime.now());
            log.setType("Product Update");
            log.setMessage("Seller deleted book with ID " + book.getBookId() + " (Title: " + book.getTitle() + ")");
            log.setRole("Seller");
            log.setRoleId(book.getSellerId());
            systemLogClient.saveLog(log);
            bookRepository.delete(book);
        } else {
            throw new BookNotFoundException(bookId + " - Book not found");
        }
    }

    @Override
    public Book updateBookById(BookPatchDTO patchedBook) {
        Optional<Book> _book = bookRepository.findBookByBookId(patchedBook.getBookId());

        if(_book.isPresent()) {
            Book updateBook = _book.get();
            if(patchedBook.getTitle() != null) updateBook.setTitle(patchedBook.getTitle());
            if(patchedBook.getCostPrice() != null) updateBook.setCostPrice(patchedBook.getCostPrice());
            if(patchedBook.getSellingPrice() != null) updateBook.setSellingPrice(patchedBook.getSellingPrice());
            if(patchedBook.getIsbn() != null) updateBook.setIsbn(patchedBook.getIsbn());
            if(patchedBook.getAuthor() != null) updateBook.setAuthor(patchedBook.getAuthor());
            if(patchedBook.getFormat() != null) updateBook.setFormat(patchedBook.getFormat());
            if(patchedBook.getPages() != null) updateBook.setPages(patchedBook.getPages());
            if(patchedBook.getPublisher() != null) updateBook.setPublisher(patchedBook.getPublisher());
            if(patchedBook.getPublishedDate() != null) updateBook.setPublishedDate(patchedBook.getPublishedDate());
            if(patchedBook.getLanguage() != null) updateBook.setLanguage(patchedBook.getLanguage());
            if(patchedBook.getWeight() != null) updateBook.setWeight(patchedBook.getWeight());
            if(patchedBook.getQuantity() != null) updateBook.setQuantity(patchedBook.getQuantity());
            if(patchedBook.getDescription() != null) updateBook.setDescription(patchedBook.getDescription());
            if(patchedBook.getAvailability() != null) updateBook.setAvailability(patchedBook.getAvailability());
            if(patchedBook.getGenre() != null) updateBook.setGenre(patchedBook.getGenre());
            if(patchedBook.getBookImage() != null) updateBook.setBookImage(patchedBook.getBookImage());
            if(patchedBook.getBookHeight() != null) updateBook.setBookHeight(patchedBook.getBookHeight());
            if(patchedBook.getBookWidth() != null) updateBook.setBookWidth(patchedBook.getBookWidth());
            bookRepository.save(updateBook);

            SystemLogDTO log = new SystemLogDTO();
            log.setTimestamp(LocalDateTime.now());
            log.setType("Product Update");
            log.setMessage("Seller updated book with ID " + updateBook.getBookId() + " (Title: " + updateBook.getTitle() + ")");
            log.setRole("Seller");
            log.setRoleId(updateBook.getSellerId());
            systemLogClient.saveLog(log);
            return updateBook;
        } else {
            throw new BookNotFoundException(patchedBook.getTitle()+" - Book not found");
        }
    }
}
