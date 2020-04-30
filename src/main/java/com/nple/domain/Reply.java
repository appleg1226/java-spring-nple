package com.nple.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nple.domain.clips.ImageClip;
import com.nple.domain.clips.WordClip;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"rereplies"})
@EqualsAndHashCode(of = "rno")
@Table(name = "tbl_reply")
@NoArgsConstructor
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ImageClip imageClip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_cno")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private WordClip wordClip;

    @OneToMany(mappedBy = "reply", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Rereply> rereplies;

    @Builder
    public Reply(String replyer, String replyContent) {
        this.replyer = replyer;
        this.replyContent = replyContent;
    }
}
