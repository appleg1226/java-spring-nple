package com.nple.persistence.clipRepositories;

import com.nple.domain.clips.WordClip;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class WordClipRepositoryImpl extends QuerydslRepositorySupport implements WordClipRepositoryCustom{
    public WordClipRepositoryImpl() {
        super(WordClip.class);
    }
}
