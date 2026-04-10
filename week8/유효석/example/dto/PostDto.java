package com.example.dto;

import lombok.*;

import com.example.entity.Post;
import com.example.entity.User;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    //기능 = DTO -> entity
    //    = entity -> DTO

    private Long id; // post 자체의 id
    private Long authorId; // author의 id가 더 필요함. 삭제/수정 권한 때문에
    private String title;
    private String content;

    public Post toEntity(User author) {
        return Post.builder().title(title).content(content).author(author).build();
    }

    public static PostDto fromEntity(Post post) {
        return PostDto.builder().id(post.getId())
                .authorId(post.getAuthor().getId()).title(post.getTitle()).content(post.getContent()).build();    }
}

