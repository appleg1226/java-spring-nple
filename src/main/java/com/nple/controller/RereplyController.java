package com.nple.controller;

import com.nple.persistence.RereplyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
public class RereplyController {

    @Autowired
    RereplyRepository repo;

    @GetMapping("/rereplies/{rno}")
    public void getListofRereply(@PathVariable("rno") Long rno, @RequestBody String page){

    }

    @PostMapping("/rereplies/{rno}")
    public void addRereply(@PathVariable("rno") Long rno){

    }

    @DeleteMapping("/rereplies/{rrno}")
    public void removeRereply(@PathVariable("rrno") Long rrno){

    }

    @PutMapping("/rereplies/{rrno}")
    public void updateRereply(@PathVariable("rrno") Long rrno){

    }

}
