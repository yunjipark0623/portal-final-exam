package com.gpch.login.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "board")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
//    자동키 생성전략
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String user_id;
    @Column(name = "write_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp writeTime;


    @OneToMany
    @JoinColumn(name = "board_id")
    private List<Comment> commentList;

    //    board와 user사이의 관계는 n:1(한 사용자가 여러 개의 게시글을 작성할 수 있다)
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;
}
