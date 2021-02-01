package com.tommy.springbootjwt.service;

import com.tommy.springbootjwt.config.SecurityUtils;
import com.tommy.springbootjwt.domain.Authority;
import com.tommy.springbootjwt.domain.User;
import com.tommy.springbootjwt.domain.UserRepository;
import com.tommy.springbootjwt.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User register(UserDto userDto) {
        String userName = userDto.getName();
        String password = passwordEncoder.encode(userDto.getPassword());
        String nickname = userDto.getNickname();

        validateUser(userDto);

        Authority authority = new Authority("ROLE_USER");

        User user = User.register(userName, password, nickname, Collections.singleton(authority));
        return userRepository.save(user);
    }

    private void validateUser(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByName(userDto.getName()).isPresent()) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String userName) {
        return userRepository.findOneWithAuthoritiesByName(userName);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtils.getCurrentUserName()
                .flatMap(userRepository::findOneWithAuthoritiesByName);
    }

}
