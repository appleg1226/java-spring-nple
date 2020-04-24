package com.nple.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nple.domain.clips.ImageClip;
import com.nple.domain.clips.WordClip;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString(exclude = {"imageClip", "wordClip"})
@EqualsAndHashCode(of = "rno")
@Table(name = "tbl_reply")
public class Reply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyer;
    private String replyContent;

    @CreationTimestamp
    private Timestamp regDate;
    @UpdateTimestamp
    private Timestamp updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_cno")
    @JsonIgnore
    private ImageClip imageClip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_cno")
    @JsonIgnore
    private WordClip wordClip;
}
