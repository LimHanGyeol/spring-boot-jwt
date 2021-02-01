package com.tommy.springbootjwt.user.controller;

import com.tommy.springbootjwt.user.dto.UserRegisterDto;
import com.tommy.springbootjwt.user.dto.UserResponseDto;
import com.tommy.springbootjwt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok().body(userService.register(userRegisterDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserResponseDto> getMyUserInfo() {
        return ResponseEntity.ok().body(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{userName}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponseDto> getUserInfo(@PathVariable String userName) {
        return ResponseEntity.ok().body(userService.getUserWithAuthorities(userName));
    }

}
