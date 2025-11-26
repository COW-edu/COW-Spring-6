package com.example.demo1.repository;

import com.example.demo1.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JPA가 실행할 때 알아서 구현
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> { // 관리 대상, 식별자 타입
}