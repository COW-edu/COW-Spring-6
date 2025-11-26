package com.example.demo1.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

    private Long id; // 게시글 번호
    private String title; // 제목
    private String content; // 내용

    // 프론트엔드는 복잡한 User 객체 전체를 보낼 수 없음
    // 대신 작성자의 식별자인 userId 숫자 값만 전송
    // 이 DTO는 그 숫자를 받아 서비스 계층으로 전달하는 역할
    private Long userId; // 작성자 ID (User 객체 아님)
}
