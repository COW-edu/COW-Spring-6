package com.example.repository;

import com.example.entity.Post;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager entityManager;

    public void save(Post post) {
        entityManager.persist(post);
    }

    public Post findById(Long id) {
        return entityManager.find(Post.class, id);
    }

    public List<Post> findAll() {
        return entityManager.createQuery("SELECT p FROM Post p ORDER BY p.id DESC", Post.class).getResultList();
    }

    public void delete(Post post) {
        entityManager.remove(post);
    }

}
