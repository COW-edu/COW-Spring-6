package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 댓글 입장에선 author이 다대일 관계임.
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public void update(String content){
        this.content = content;
    }

    public void setPost(Post post){
        this.post = post;
    }
}
