package com.example.repository;

import com.example.entity.Comment;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor

public class CommentRepository {
    // comment들을 가지고 있는 entityManager을 통해 각 entity에 접근한다.

    private final EntityManager entityManager;

    public void save(Comment comment){
        entityManager.persist(comment);
    }

    public Comment findById(Long id){
        return entityManager.find(Comment.class, id);
    }

    public List<Comment> findAllCommentsByPostId(Long postId){
        return entityManager.createQuery("SELECT c FROM Comment c" + "WHERE c.post.id = :postId " + "ORDER BY c.id ASC", Comment.class).
                setParameter("postId", postId).getResultList();
    }

    public void delete(Comment comment){
        entityManager.remove(comment);
    }
}
