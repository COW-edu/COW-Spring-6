package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자 -> protected로 생성
@AllArgsConstructor
@Builder
public class User {

    @Id// primary key = id다 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id가 안들어오면 자동으로 넣어줘.
    private Long id;

    private String name;
    private String email;

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
