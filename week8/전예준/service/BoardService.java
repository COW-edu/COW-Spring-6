package com.example.demo1.service;

import com.example.demo1.dto.BoardDto;
import com.example.demo1.entity.Board;
import com.example.demo1.entity.User;
import com.example.demo1.repository.BoardRepository;
import com.example.demo1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기 모드 (성능 최적화)
public class BoardService {

    // 게시글을 저장하려면, 두 개의 repository 필요
    private final BoardRepository boardRepository; // 게시글 저장용
    private final UserRepository userRepository;   // 작성자 찾기용

    // 게시글 저장 기능
    // 모두 성공해야 저장, 하나라도 실패하면 롤백
    @Transactional
    public void createBoard(Long userId, BoardDto boardDto) {

        // 작성자 찾기 (에러 처리 포함)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("작성자가 없어요! id=" + userId));

        // 게시글 엔티티 생성 (작성자 정보 포함)
        // @Getter, @Builder 롬복 어노테이션
        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .user(user) // ID 대신 객체 전체
                .build();

        // 저장
        // JPA가 알아서 user의 ID를 뽑아서 DB에 저장
        boardRepository.save(board);
    }
}