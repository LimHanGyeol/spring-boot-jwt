package com.tommy.springbootjwt.user.dto;

import com.tommy.springbootjwt.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private String nickname;
    private boolean activated;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.activated = user.isActivated();
    }

}
