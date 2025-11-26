package com.example.demo1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스가 알아서 ID 설정
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    // 1:N 관계 설정
    // FetchType.LAZY: board.getUser()를 호출하기 전까지는 DB 조회를 미룸
    // 유저 정보는 나중에 필요할 때 가져오기 때문에 성능 최적화
    @ManyToOne(fetch = FetchType.LAZY)

    // DB에 user_id라는 이름으로 외래키 컬럼 생성
    @JoinColumn(name = "user_id")
    // DB 테이블에는 user_id가 들어가지만, JPA 관점에서는 User 객체 자체를 필드로 참조
    private User user;

}
