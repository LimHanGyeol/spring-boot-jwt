package com.tommy.springbootjwt.user.service;

import com.tommy.springbootjwt.config.SecurityUtils;
import com.tommy.springbootjwt.user.domain.User;
import com.tommy.springbootjwt.user.domain.UserRepository;
import com.tommy.springbootjwt.user.dto.UserRegisterDto;
import com.tommy.springbootjwt.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto register(UserRegisterDto userRegisterDto) {
        String userName = userRegisterDto.getName();
        String password = passwordEncoder.encode(userRegisterDto.getPassword());
        String nickname = userRegisterDto.getNickname();

        validateUser(userName);

        User user = User.registerCustomer(userName, password, nickname);
        return userRepository.save(user)
                .toUserResponseDto();
    }

    private void validateUser(String userName) {
        if (userRepository.findOneWithAuthoritiesByName(userName).isPresent()) {
            throw new RuntimeException("exist user : " + userName);
        }
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserWithAuthorities(String userName) {
        return userRepository.findOneWithAuthoritiesByName(userName)
                .map(UserResponseDto::new)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional(readOnly = true)
    public UserResponseDto getMyUserWithAuthorities() {
        return SecurityUtils.getCurrentUserName()
                .flatMap(userRepository::findOneWithAuthoritiesByName)
                .map(UserResponseDto::new)
                .orElseThrow(RuntimeException::new);
    }

}
