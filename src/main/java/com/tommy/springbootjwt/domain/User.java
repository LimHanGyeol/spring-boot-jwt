package com.tommy.springbootjwt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @JsonIgnore
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    private User(Long id, String name, String password, String nickname) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.activated = true;
    }

    public static User register(Long id, String name, String password, String nickname) {
        return new User(id, name, password, nickname);
    }

}
