package com.example.demo4.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
