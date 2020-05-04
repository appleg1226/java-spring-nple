package com.nple.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString(exclude = "reply")
@EqualsAndHashCode(of = "rrno")
@Table(name = "tbl_rereply")
@NoArgsConstructor
public class Rereply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rrno;

    private String replyer;
    private String replyContent;

    @CreationTimestamp
    private Timestamp regDate;
    @UpdateTimestamp
    private Timestamp updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rno")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Reply reply;

    @Builder
    public Rereply(String replyer, String replyContent) {
        this.replyer = replyer;
        this.replyContent = replyContent;
    }
}
