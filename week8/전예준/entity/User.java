package com.example.demo1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity //DB 테이블 선언
@Table(name = "users") //Entity와 매핑할 DB 테이블의 이름이나 세부 설정 지정
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 기본 생성자 필수
@AllArgsConstructor
public class User {
    @Id  //기본키 지정(모든 행을 구분하는 식별자)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스가 알아서 1씩 증가
    private Long id;

    @Column(nullable = false, length = 10) //빈 값 x, 길이 10 제한
    private String name;

    @Column(nullable = false, unique = true) //빈 값 x, 중복 x
    private String email;

    //캡슐화(데이터, 책임), 데이터 보호
    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // 1:N 관계에서 1쪽에 해당하는 엔티티가 N쪽 엔티티 목록을 조회하기 위한 매핑
    // 이 리스트는 DB에 컬럼으로 존재하지 않고, JPA가 알아서 글 목록을 가져와서 채워주는 가짜 리스트
    // 반대편 엔티티에서 나를 참조하고 있는 필드 명수명을 적음
    // 이 필드에 데이터를 추가하거나 변경해도 DB 외래키(FK)에는 영향을 주지 않음
    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();


}
