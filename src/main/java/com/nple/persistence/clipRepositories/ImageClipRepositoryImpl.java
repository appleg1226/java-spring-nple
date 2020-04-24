package com.nple.persistence.clipRepositories;

import com.nple.domain.Clip;
import com.nple.domain.clips.ImageClip;
import com.nple.domain.clips.QImageClip;
import com.nple.domain.clips.QWordClip;
import com.nple.domain.clips.WordClip;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImageClipRepositoryImpl extends QuerydslRepositorySupport implements ImageClipRepositoryCustom {
    public ImageClipRepositoryImpl() { super(ImageClip.class); }

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

}
