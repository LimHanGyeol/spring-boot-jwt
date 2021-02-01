package com.tommy.springbootjwt.user.controller;

import com.tommy.springbootjwt.user.dto.UserRegisterDto;
import com.tommy.springbootjwt.user.dto.UserResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    // TODO : Security Test 설정 공부 후 테스트 코드 다시 작성하기
    @Test
    void user_register() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("hangyeol", "123sssa", "tommy");
        HttpEntity<UserRegisterDto> request = new HttpEntity<>(userRegisterDto);

        ResponseEntity<UserResponseDto> userResponseDto = testRestTemplate.postForEntity("/api/register", request, UserResponseDto.class);
        assertThat(userResponseDto.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}
