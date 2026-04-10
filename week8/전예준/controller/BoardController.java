package com.example.demo1.controller;

import com.example.demo1.dto.BoardDto;
import com.example.demo1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // JSON을 반환하는 컨트롤러임을 명시
@RequestMapping("/boards") // 해당 클래스의 모든 주소는 /boards로 시작
@RequiredArgsConstructor // 서비스 자동 연결 (생성자 주입)
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성 API
    // POST /boards?userId=1
    // Body: {"title":"...", "content": "..." }
    @PostMapping
    public void createBoard(@RequestParam Long userId, @RequestBody BoardDto boardDto){
        // 서비스에 Id와 게시글 전달
        boardService.createBoard(userId, boardDto);
    }
}
