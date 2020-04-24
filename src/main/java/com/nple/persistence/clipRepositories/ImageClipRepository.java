package com.nple.persistence.clipRepositories;

import com.nple.domain.clips.ImageClip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageClipRepository extends JpaRepository<ImageClip, Long>, ImageClipRepositoryCustom {
}