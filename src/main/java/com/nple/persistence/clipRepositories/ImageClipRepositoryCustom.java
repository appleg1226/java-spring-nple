package com.nple.persistence.clipRepositories;

import com.nple.domain.Clip;
import java.util.List;

public interface ImageClipRepositoryCustom {
    public List<Clip> getClipsofBook(Long bno);
}
