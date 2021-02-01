package com.tommy.springbootjwt.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void user_create() {
        User user = User.register("hangyeol", "1234", "tommy", Collections.singleton(new Authority("ROLE_USER")));
        assertAll(
                () -> assertThat(user).isNotNull(),
                () -> assertThat(user.getName()).isEqualTo("hangyeol"),
                () -> assertThat(user.getPassword()).isEqualTo("1234"),
                () -> assertThat(user.getNickname()).isEqualTo("tommy"),
                () -> assertThat(user.isActivated()).isTrue()
        );
    }

}
