package com.example.repository;

import com.example.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    // 저장
    public void save(User user) {
        em.persist(user);   // INSERT 자동 실행
    }

    // 전체 찾기
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    //하나의 객체만 id로 찾기
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public void delete(User user) {
        em.remove(user);
    }

}
