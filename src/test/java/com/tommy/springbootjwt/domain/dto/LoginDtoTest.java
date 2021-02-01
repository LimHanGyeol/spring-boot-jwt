package com.tommy.springbootjwt.domain.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoginDtoTest {

    @Test
    void loginDto_create() {
        LoginDto loginDto = new LoginDto("hangyeol", "admin");
        assertThat(loginDto).isNotNull();
    }

}
