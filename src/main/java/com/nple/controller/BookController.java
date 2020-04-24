package com.nple.controller;

import com.nple.domain.Book;
import com.nple.persistence.BookRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Log
public class BookController {

    @Autowired
    BookRepository bookRepo;

    @GetMapping("/book/{bno}")
    public ResponseEntity<Book> getBookObject(@PathVariable("bno") Long bno){
        log.info("getting book" + bno +"information");
        Optional<Book> result = bookRepo.findById(bno);
        return new ResponseEntity<>(result.orElse(null), HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<String> addBookObject(@RequestBody Book book){
        log.info("adding book");
        bookRepo.save(book);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @DeleteMapping("/book/{bno}")
    public ResponseEntity<String> removeBookObject(@PathVariable("bno") Long bno){
        log.info("Deleting book " + bno);
        bookRepo.deleteById(bno);
        return ResponseEntity.ok("success");
    }

    @PutMapping("/book/{bno}")
    public ResponseEntity<String> updateBookObject(@PathVariable("bno") Long bno, @RequestBody Book book){
        log.info("Updating book " + bno);
        bookRepo.findById(bno).ifPresent(originalBook -> {
            originalBook.setThumbDir(book.getThumbDir());
            originalBook.setWriter(book.getWriter());
            originalBook.setTitle(book.getTitle());
            bookRepo.save(originalBook);
        });
        return ResponseEntity.ok("success");
    }
}
