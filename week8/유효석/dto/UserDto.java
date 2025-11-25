package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.entity.User;

@Getter
@Builder
@AllArgsConstructor // 생성자를 자동으로 만들어주는 것.
@NoArgsConstructor

public class UserDto { // data를 front-end로 전달

    private Long id;
    private String name;
    private String email;


    public User toEntity(){
        // DTO -> entity로 변환
        // 간단한 버전!
        return new User(id, name, email);

        // 나중엔 아래 방식으로 바꾸는게 좋음.
        /*
        User user = User.builder().id(id).name(name).email(email).build()
         */
    }

    public static UserDto from(User user){
        return UserDto.builder().id(user.getId()).name(user.getName())
                .email(user.getEmail()).build();
    }


}
