package com.tommy.springbootjwt.user.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserLoginDtoTest {

    @Test
    void loginDto_create() {
        UserLoginDto userLoginDto = new UserLoginDto("hangyeol", "admin");
        assertThat(userLoginDto).isNotNull();
    }

}
