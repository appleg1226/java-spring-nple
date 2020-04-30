package com.nple.persistence;

import com.nple.domain.Book;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log
@Commit
class BookRepositoryTest {

    @Autowired
    BookRepository repo;

    @Test
    public void testInsert(){
        IntStream.range(0, 100).forEach(i->{
            Book book = new Book();

            book.setTitle("Book" + String.format("%03d", i));
            book.setWriter("Writer" + String.format("%03d", i));
            book.setThumbDir("image directory");

            repo.save(book);
        });
    }

    @Test
    @Transactional
    public void deleteBooks(){
        repo.deleteById(2L);
    }


}