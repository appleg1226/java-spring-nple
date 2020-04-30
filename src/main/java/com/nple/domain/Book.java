package com.nple.domain;

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
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "bno")
@ToString(exclude = {"imageClips", "wordClips"})
@Table(name = "tbl_book")
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String writer;

    private String thumbDir;

    @CreationTimestamp
    private Timestamp regDate;
    @UpdateTimestamp
    private Timestamp updateDate;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<ImageClip> imageClips;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<WordClip> wordClips;
}
