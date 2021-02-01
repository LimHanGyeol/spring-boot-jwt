package com.tommy.springbootjwt.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tommy.springbootjwt.user.dto.UserResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @JsonIgnore
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String nickname;

    @JsonIgnore
    @Column(nullable = false)
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    private User(String name, String password, String nickname, Set<Authority> authorities) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.authorities = authorities;
        this.activated = true;
    }

    public static User registerCustomer(String name, String password, String nickname) {
        Authority authority = new Authority("ROLE_USER");
        return new User(name, password, nickname, Collections.singleton(authority));
    }

    public UserResponseDto toUserResponseDto() {
        return new UserResponseDto(this);
    }

}
