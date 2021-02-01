package com.tommy.springbootjwt.controller;

import com.tommy.springbootjwt.domain.User;
import com.tommy.springbootjwt.domain.dto.UserDto;
import com.tommy.springbootjwt.service.UserService;
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

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        log.info("request");
        return ResponseEntity.ok().body("hello");
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(userService.register(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok().body(userService
                .getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{userName}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String userName) {
        return ResponseEntity.ok().body(userService.getUserWithAuthorities(userName).get());
    }

}
