package com.example.dto;

import com.example.entity.Comment;
import com.example.entity.User;
import com.example.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private Long postId;
    private Long authorId;
    private String content;

    //DTO -> entity
    // entity -> DTO

    public Comment toEntity(User author, Post post){
        return Comment.builder().content(this.content).author(author).post(post).build();
    }
}
