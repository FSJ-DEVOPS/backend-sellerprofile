package com.shopverse.sellerprofile.repository;

import com.shopverse.sellerprofile.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {
    Optional<Book> findBookByBookId(String bookId);
    Optional<List<Book>> findBySellerId(String sellerId);
}
