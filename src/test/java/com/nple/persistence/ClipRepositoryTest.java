package com.nple.persistence;

import com.nple.domain.Book;
import com.nple.domain.clips.ImageClip;
import com.nple.domain.clips.WordClip;
import com.nple.persistence.clipRepositories.ImageClipRepository;
import com.nple.persistence.clipRepositories.WordClipRepository;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
@Log
@Commit
class ClipRepositoryTest {

    @Autowired
    ImageClipRepository imageRepo;
    @Autowired
    WordClipRepository wordRepo;

    @Test
    public void imageClipInsertTest() {

        LongStream.range(1, 21).forEach(bookNum -> {
            Book book = new Book();
            book.setBno(bookNum);

            IntStream.range(0, 30).forEach(clip -> {
                ImageClip imageClip = new ImageClip();
                imageClip.setBook(book);
                imageClip.setImageDir("image directory " + String.format("%02d", bookNum));
                imageRepo.save(imageClip);
            });


        });

        LongStream.range(41, 61).forEach(bookNum -> {
            Book book = new Book();
            book.setBno(bookNum);

            IntStream.range(0, 30).forEach(clip -> {
                ImageClip imageClip = new ImageClip();
                imageClip.setImageDir("image directory " + String.format("%02d", bookNum));
                imageClip.setBook(book);
                imageRepo.save(imageClip);
            });
        });

        LongStream.range(81, 101).forEach(bookNum -> {
            Book book = new Book();
            book.setBno(bookNum);

            IntStream.range(0, 30).forEach(clip -> {
                ImageClip imageClip = new ImageClip();
                imageClip.setImageDir("image directory " + String.format("%02d", bookNum));
                imageClip.setBook(book);
                imageRepo.save(imageClip);
            });
        });

    }

    @Test
    @Transactional
    public void wordClipInsertTest(){

        LongStream.range(1, 3).forEach(bookNum -> {

            Book book = new Book();
            book.setBno(bookNum);

            IntStream.range(0, 5).forEach(clip -> {

                WordClip wordClip = new WordClip();
                wordClip.setParagraph("페이지 " + String.format("%02d", clip));

                wordClip.setBook(book);

                wordRepo.save(wordClip);
            });
        });

//        LongStream.range(61, 81).forEach(bookNum -> {
//
//            Book book = new Book();
//            book.setBno(bookNum);
//
//            IntStream.range(0, 30).forEach(clip -> {
//
//                WordClip wordClip = new WordClip();
//                wordClip.setParagraph("페이지 " + String.format("%02d", clip));
//
//                wordClip.setBook(book);
//
//                wordRepo.save(wordClip);
//            });
//        });
    }

    @Test
    public void getListofClips(){
        imageRepo.getClipsofBook(1L).forEach(clip->{
            log.info(""+ clip);
        });
    }

}