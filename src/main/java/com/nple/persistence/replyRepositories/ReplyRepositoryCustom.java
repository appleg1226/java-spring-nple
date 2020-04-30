package com.nple.persistence.replyRepositories;

import com.nple.domain.Reply;

import java.util.List;

public interface ReplyRepositoryCustom{
    List<Reply> findByCnoOfImage(Long cno, Long offset);
    List<Reply> findByCnoOfWord(Long cno, Long offset);
}
