package com.nple.persistence;

import com.nple.domain.Reply;
import com.nple.domain.clips.ImageClip;
import com.nple.domain.clips.WordClip;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
@Log
@Commit
class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepo;

    @Test
    public void addImageClipDummyReplies(){
        LongStream stream1 = LongStream.range(1, 11);
        LongStream stream2 = LongStream.range(31, 41);
        LongStream stream3 = LongStream.concat(stream1, stream2);

        stream3.forEach(cno->{
            ImageClip imageClip = new ImageClip();
            imageClip.setCno(cno);
            IntStream.range(0, 20).forEach(i->{
                Reply reply = new Reply();
                reply.setReplyer("replyer" + cno);
                reply.setReplyContent("샘플 댓글 " + i);
                reply.setImageClip(imageClip);
                replyRepo.save(reply);
            });
        });
    }

    @Test
    public void addWordClipDummyReplies(){
        LongStream stream1 = LongStream.range(21, 31);
        LongStream stream2 = LongStream.range(61, 71);
        LongStream stream3 = LongStream.concat(stream1, stream2);

        stream3.forEach(cno->{
            WordClip wordClip = new WordClip();
            wordClip.setCno(cno);
            IntStream.range(0, 20).forEach(i->{
                Reply reply = new Reply();
                reply.setReplyer("replyer" + cno);
                reply.setReplyContent("샘플 댓글 " + i);
                reply.setWordClip(wordClip);
                replyRepo.save(reply);
            });
        });
    }

}