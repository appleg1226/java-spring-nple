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
import org.springframework.web.bind.annotation.*;

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

        List<ImageClip> imageClipList = Arrays.asList(clipList.iclip);
        List<WordClip> wordClipList = Arrays.asList(clipList.wclip);

        imageClipList.forEach(imageClip -> {
            Book book = new Book();
            book.setBno(bno);
            imageClip.setBook(book);
            imageRepo.save(imageClip);
        });

        wordClipList.forEach(wordClip -> {
            Book book = new Book();
            book.setBno(bno);
            wordClip.setBook(book);
            wordRepo.save(wordClip);
        });

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
