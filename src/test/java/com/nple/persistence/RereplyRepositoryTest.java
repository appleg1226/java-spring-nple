package com.nple.persistence;

import com.nple.domain.Reply;
import com.nple.domain.Rereply;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log
@Commit
class RereplyRepositoryTest {

    @Autowired
    RereplyRepository rereplyRepo;

    @Test
    public void addDummyRereply(){
        LongStream.of(1L, 2L, 3L, 10L, 11L, 12L).forEach(rno->{
            Reply reply = new Reply();
            reply.setRno(rno);

            IntStream.range(0, 20).forEach(i->{
                Rereply rereply = new Rereply();
                rereply.setReplyContent("대댓글" + i);
                rereply.setReplyer("rereplyer: " + rno);
                rereply.setReply(reply);

                rereplyRepo.save(rereply);
            });
        });
    }

    @Test
    @Transactional
    public void testGetListofRereplies(){
        // page가 1이면 2페이지다.
        Pageable pageable = PageRequest.of(1, 3);
        rereplyRepo.getRerepliesOfOneReply(1L, pageable).forEach(System.out::println);
    }

}