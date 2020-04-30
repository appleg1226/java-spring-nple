package com.nple.persistence.replyRepositories;

import com.nple.domain.QReply;
import com.nple.domain.Reply;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ReplyRepositoryImpl extends QuerydslRepositorySupport implements ReplyRepositoryCustom {
    public ReplyRepositoryImpl() {
        super(Reply.class);
    }

    @Override
    public List<Reply> findByCnoOfImage(Long cno, Long offset) {
        QReply reply = QReply.reply;

        return from(reply)
                .where(reply.imageClip.cno.eq(cno))
                .offset(offset)
                .limit(10L)
                .fetch();
    }

    @Override
    public List<Reply> findByCnoOfWord(Long cno, Long offset) {
        QReply reply = QReply.reply;

        return from(reply)
                .where(reply.wordClip.cno.eq(cno))
                .offset(offset)
                .limit(10L)
                .fetch();
    }
}
