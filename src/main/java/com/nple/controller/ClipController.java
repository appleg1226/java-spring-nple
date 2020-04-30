package com.nple.controller;

import com.nple.domain.Book;
import com.nple.domain.Clip;
import com.nple.domain.clips.ClipWrapper;
import com.nple.domain.clips.ImageClip;
import com.nple.domain.clips.WordClip;
import com.nple.persistence.clipRepositories.ImageClipRepository;
import com.nple.persistence.clipRepositories.WordClipRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.List;

@RestController
@Log
public class ClipController {

    @Autowired
    ImageClipRepository imageRepo;

    @Autowired
    WordClipRepository wordRepo;

    @GetMapping("/book/clip/{bno}")
    public ResponseEntity<List<Clip>> getClipList(@PathVariable("bno") Long bno){
        log.info("Getting Clip List from Book " + bno);
        return new ResponseEntity<>(imageRepo.getClipsofBook(bno), HttpStatus.OK);
    }

    @PostMapping("/book/clip/{bno}")
    public ResponseEntity<String> addClips(@PathVariable("bno") Long bno, @RequestBody ClipWrapper clipList){

        try{
            List<ImageClip> imageClipList = Arrays.asList(clipList.iclip);
            imageClipList.forEach(imageClip -> {
                Book book = new Book();
                book.setBno(bno);
                imageClip.setBook(book);
                imageRepo.save(imageClip);
            });

        } catch (NullPointerException e) {
            log.info("nullpointer");
        }

        try{
            List<WordClip> wordClipList = Arrays.asList(clipList.wclip);
            wordClipList.forEach(wordClip -> {
                Book book = new Book();
                book.setBno(bno);
                wordClip.setBook(book);
                wordRepo.save(wordClip);
            });
        } catch (NullPointerException e){
            log.info("nullpointer");
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/book/clip/{bno}")
    @Transactional
    public ResponseEntity<String> updateClips(@PathVariable("bno") Long bno,
                                              @RequestBody ClipWrapper clipList) {
        log.info("===============updating clips======================");


        Long result = imageRepo.deleteClipsofBook(bno);
        log.info("deleted clips(update): " + result);
        final Long[] count = {0L};

        Book book = new Book();
        book.setBno(bno);

        try{
            List<ImageClip> imageClipList = Arrays.asList(clipList.iclip);
            imageClipList.forEach(imageClip -> {
                log.info("imageClips: " + imageClip.toString());
                imageClip.setBook(book);
                imageRepo.save(imageClip);
                count[0]++;
            });
        } catch (NullPointerException e){
            log.info("nullpointer");
        }

        try {
            List<WordClip> wordClipList = Arrays.asList(clipList.wclip);
            wordClipList.forEach(wordClip -> {
                log.info("wordClips: " + wordClip.toString());
                wordClip.setBook(book);
                wordRepo.save(wordClip);
                count[0]++;
            });
        } catch (NullPointerException e) {
            log.info("nullpointer");
        }

        log.info("updated clip(add): " + count[0]);
        log.info("==============end of update clips===================");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/book/clip/{bno}")
    public ResponseEntity<String> deleteClips(@PathVariable("bno") Long bno){
        Long result = imageRepo.deleteClipsofBook(bno);
        log.info("deleted clips: " + result);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
