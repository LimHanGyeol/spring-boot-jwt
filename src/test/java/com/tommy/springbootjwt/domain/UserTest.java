package com.tommy.springbootjwt.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void user_create() {
        User user = User.register(1L, "hangyeol", "1234", "tommy");
        assertAll(
                () -> assertThat(user).isNotNull(),
                () -> assertThat(user.getId()).isEqualTo(1L),
                () -> assertThat(user.getName()).isEqualTo("hangyeol"),
                () -> assertThat(user.getPassword()).isEqualTo("1234"),
                () -> assertThat(user.getNickname()).isEqualTo("tommy"),
                () -> assertThat(user.isActivated()).isTrue()
        );
    }

}
