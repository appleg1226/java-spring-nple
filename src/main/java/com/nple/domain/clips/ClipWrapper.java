package com.nple.domain.clips;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

@AllArgsConstructor
public class ClipWrapper {
    public ImageClip[] iclip;
    public WordClip[] wclip;

    @Override
    public String toString() {
        return "ClipWrapper{" +
                "iclip=" + Arrays.toString(iclip) +
                ", wclip=" + Arrays.toString(wclip) +
                '}';
    }
}
