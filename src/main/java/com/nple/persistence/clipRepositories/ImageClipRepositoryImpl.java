package com.nple.persistence.clipRepositories;

import com.nple.domain.Clip;
import com.nple.domain.clips.ImageClip;
import com.nple.domain.clips.QImageClip;
import com.nple.domain.clips.QWordClip;
import com.nple.domain.clips.WordClip;
import com.querydsl.jpa.impl.JPADeleteClause;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log
public class ImageClipRepositoryImpl extends QuerydslRepositorySupport implements ImageClipRepositoryCustom {
    public ImageClipRepositoryImpl() { super(ImageClip.class); }

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Clip> getClipsofBook(Long bno) {
        QImageClip imageClip = QImageClip.imageClip;
        QWordClip wordClip = QWordClip.wordClip;

        List<ImageClip> imageList = from(imageClip)
                .where(imageClip.book.bno.eq(bno))
                .fetch();

        List<WordClip> wordList = from(wordClip)
                .where(wordClip.book.bno.eq(bno))
                .fetch();

        return Stream.of(imageList, wordList).flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public Long deleteClipsofBook(Long bno) {
        QImageClip imageClip = QImageClip.imageClip;
        QWordClip wordClip = QWordClip.wordClip;

        Long deletedCount = new JPADeleteClause(entityManager, imageClip).where(imageClip.book.bno.eq(bno)).execute();
        Long deletedCount2 = new JPADeleteClause(entityManager, wordClip).where(wordClip.book.bno.eq(bno)).execute();

        return deletedCount+deletedCount2;
    }
}
