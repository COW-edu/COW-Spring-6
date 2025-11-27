package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.entity.User;
import com.exaple.entity.UserRole

@Getter
@Builder
@AllArgsConstructor // 생성자를 자동으로 만들어주는 것.
@NoArgsConstructor

public class UserDto { // data를 front-end로 전달

    private Long id;
    private String name;
    private String email;
    private UserRole role;


    public User toEntity(){

        User user = User.builder().id(id).name(name).email(email).userRole(role).build();
        return user;
    }

    public static UserDto from(User user){
        return UserDto.builder().id(user.getId()).name(user.getName())
                .email(user.getEmail()).role(user.getUserRole()).build();
    }


}
