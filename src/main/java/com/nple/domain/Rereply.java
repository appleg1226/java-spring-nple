package com.nple.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ToString(exclude = "reply")
@EqualsAndHashCode(of = "rrno")
@Table(name = "tbl_rereply")
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
    private Reply reply;
}
