package com.tommy.springbootjwt.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserTest {

    @Test
    void user_create() {
        User user = User.registerCustomer("hangyeol", "1234", "tommy");
        assertAll(
                () -> assertThat(user).isNotNull(),
                () -> assertThat(user.getName()).isEqualTo("hangyeol"),
                () -> assertThat(user.getPassword()).isEqualTo("1234"),
                () -> assertThat(user.getNickname()).isEqualTo("tommy"),
                () -> assertThat(user.isActivated()).isTrue()
        );
    }

}
