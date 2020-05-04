package com.nple.persistence;

import com.nple.domain.Rereply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RereplyRepository extends JpaRepository<Rereply, Long> {

    @Query("select r from Rereply r where r.reply.rno = ?1")
    List<Rereply> getRerepliesOfOneReply(Long rno, Pageable pageable);


}
