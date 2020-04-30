package com.nple.domain.clips;

import com.nple.domain.Clip;
import com.nple.domain.Reply;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = "replies")
@Table(name = "tbl_wordclip")
public class WordClip extends Clip {

    private String paragraph;

    @OneToMany(mappedBy = "wordClip", cascade = CascadeType.REMOVE)
    private List<Reply> replies;
}
