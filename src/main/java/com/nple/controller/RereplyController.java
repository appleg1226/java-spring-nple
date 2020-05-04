package com.nple.controller;

import com.nple.domain.Reply;
import com.nple.domain.Rereply;
import com.nple.persistence.RereplyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log
public class RereplyController {

    @Autowired
    RereplyRepository repo;

    @GetMapping("/rereplies/{rno}")
    public ResponseEntity<List<Rereply>> getListofRereply(@PathVariable("rno") Long rno, @RequestBody String page){
        log.info("rereply controller: getting list of Rereply of reply: " + rno);
        List<Rereply> body = repo.getRerepliesOfOneReply(rno, PageRequest.of(Integer.parseInt(page), 10));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/rereplies/{rno}")
    public ResponseEntity<String> addRereply(@PathVariable("rno") Long rno, @RequestBody Rereply rereply){
        log.info("rereply controller: adding Rereply");

        Reply reply = new Reply();
        reply.setRno(rno);
        rereply.setReply(reply);

        repo.save(rereply);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/rereplies/{rrno}")
    public ResponseEntity<String> removeRereply(@PathVariable("rrno") Long rrno){
        log.info("rereply controller: removing Rereply of:" + rrno);

        repo.deleteById(rrno);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/rereplies/{rrno}")
    public ResponseEntity<String> updateRereply(@PathVariable("rrno") Long rrno, @RequestBody Rereply rereply){
        log.info("rereply controller: updating Rereply");

        Rereply result = repo.findById(rrno).orElseThrow(NullPointerException::new);
        result.setReplyContent(rereply.getReplyContent());
        result.setReplyer(rereply.getReplyer());

        repo.save(result);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
