package com.nple.controller;

import com.nple.domain.Reply;
import com.nple.domain.clips.ImageClip;
import com.nple.domain.clips.WordClip;
import com.nple.persistence.replyRepositories.ReplyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log
public class ReplyController {

    @Autowired
    ReplyRepository repo;

    @DeleteMapping("book/clip/replies/{cno}/{rno}")
    public ResponseEntity<String> deleteReply(@PathVariable("cno") Long cno, @PathVariable("rno") Long rno){
        log.info("delete reply of clip: " + cno);
        Reply result = repo.findById(rno).orElse(null);
        log.info("deleted reply: " + result);
        repo.deleteById(rno);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("book/clip/replies/{cno}/{rno}")
    public ResponseEntity<String> updateReply(@PathVariable("cno") Long cno,
                                              @PathVariable("rno") Long rno,
                                              @RequestBody Reply reply){
        log.info("update reply of clip:" + cno);
        log.info("before update:" + repo.findById(rno).orElse(null));
        log.info("after update: " + reply.toString());


        repo.findById(rno).ifPresent(rep -> {
            rep.setReplyer(reply.getReplyer());
            rep.setReplyContent(reply.getReplyContent());
            repo.save(rep);
        });

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /*
     *
     *  여기서부터 Image
     *
     * */

    @GetMapping("/book/clip/replies/image/{cno}")
    public ResponseEntity<List<Reply>> getReplyListofImage(@PathVariable("cno") Long cno, @RequestBody String page){

        log.info("cno: " + cno + ", page: " + page);

        repo.findByCnoOfImage(cno, Long.parseLong(page)).forEach(reply -> {
            log.info("reply: " + reply + "\n");
        });

        return new ResponseEntity<>(repo.findByCnoOfImage(cno, Long.parseLong(page)), HttpStatus.OK);
    }

    @PostMapping("book/clip/replies/image/{cno}")
    public ResponseEntity<String> addReplyToImage(@PathVariable("cno") Long cno, @RequestBody Reply reply){

        ImageClip imageClip = new ImageClip();
        imageClip.setCno(cno);

        log.info("add reply to clip: " + cno);

        reply.setImageClip(imageClip);
        Reply result = repo.save(reply);

        log.info("" + result);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /*
    *
    *  여기서부터 Word
    *
    * */

    @GetMapping("/book/clip/replies/word/{cno}")
    public ResponseEntity<List<Reply>> getReplyListofWord(@PathVariable("cno") Long cno, @RequestBody String page){

        log.info("cno: " + cno + ", page: " + page);

        repo.findByCnoOfWord(cno, Long.parseLong(page)).forEach(reply -> {
            log.info("reply: " + reply + "\n");
        });

        return new ResponseEntity<>(repo.findByCnoOfWord(cno, Long.parseLong(page)), HttpStatus.OK);
    }

    @PostMapping("book/clip/replies/word/{cno}")
    public ResponseEntity<String> addReplyToWord(@PathVariable("cno") Long cno, @RequestBody Reply reply){

        WordClip wordClip = new WordClip();
        wordClip.setCno(cno);

        log.info("add reply to clip: " + cno);

        reply.setWordClip(wordClip);
        Reply result = repo.save(reply);

        log.info("" + result);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
