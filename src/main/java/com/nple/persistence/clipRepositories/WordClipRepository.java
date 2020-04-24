package com.nple.persistence.clipRepositories;

import com.nple.domain.clips.WordClip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordClipRepository extends JpaRepository<WordClip, Long>, WordClipRepositoryCustom {
}