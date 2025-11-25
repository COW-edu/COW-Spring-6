package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id가 설정이 안될 때 자동으로 값을 넣어줌!
    private long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 다중성 관계 설정 게시판 입장에선 user와 다대일
    private User author; // user를 작성자로 받아온다.

    @OneToMany(fetch = FetchType.LAZY) // 게시판 입장에선 comment와 일대다
    private List<Comment> comments = new ArrayList<>();

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void addComment(Comment comment){
        comments.add(comment);
        comment.setPost(this); // 나중에 구현을 어떻게 할지 생각해봐야 함. comment쪽에도 post해야 함.
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
        comment.setPost(null);
    }
}
