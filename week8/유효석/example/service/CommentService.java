package com.example.service;

import com.example.dto.CommentDto;
import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.entity.UserRole;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import com.example.repository.PostRepository;

import com.example.repository.UserRepository;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createComment(CommentDto commentDto){
        Post post = postRepository.findById(commentDto.getPostId());
        if(post == null){
            throw new EntityNotFoundException("Post not found");
        }

        User author = userRepository.findById(commentDto.getAuthorId());
        if(author == null){
            throw new EntityNotFoundException("Author not found");
        }

        // post / user 둘 다 실존함.

        Comment comment = commentDto.toEntity(author, post); // comment 생성

        commentRepository.save(comment);

        return comment.getId();
    }

    @Transactional
    public void updateComment(Long commentId, Long requesterId, String newContent){
        Comment comment = commentRepository.findById(commentId);
        if(comment == null){
            throw new EntityNotFoundException("Comment not found");
        }

        //comment실존한다면,

        User author = userRepository.findById(requesterId);
        if(author == null){
            throw new EntityNotFoundException("Requester not found");
        }

        // author 실존한다면,

        if(author.isAdmin() || author.getId() == comment.getAuthor().getId()){
            comment.update(newContent);
        }
        else{
            throw new EntityNotFoundException("No authority to update");
        }
    }

    @Transactional
    public void deleteComment(Long commentId, Long requesterId){
        Comment comment = commentRepository.findById(commentId);
        if(comment == null){
            throw new EntityNotFoundException("Comment not found");
        }

        User author = userRepository.findById(requesterId);
        if(author == null){
            throw new EntityNotFoundException("Requester not found");
        }

        if(author.isAdmin() || author.getId() == comment.getAuthor().getId()){
            commentRepository.delete(comment);
        }
        else{
            throw new EntityNotFoundException("No authority to delete");
        }
    }
}
