package com.aga.intern_intelligence_movie_api.controller;

import com.aga.intern_intelligence_movie_api.model.ChangePasswordRequest;
import com.aga.intern_intelligence_movie_api.model.UserDto;
import com.aga.intern_intelligence_movie_api.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserDto userDto) {
        userService.register(userDto,false);
        return "User registered successfully!";
    }

    @PostMapping("/change-password")
    @ResponseStatus(HttpStatus.OK)
    public String changePassword(@RequestBody ChangePasswordRequest request, HttpServletRequest httpRequest) {
        userService.changePassword(request.getUsername(), request.getOldPassword(), request.getNewPassword(), httpRequest);
        return "Password changed successfully! Please log in again.";
    }

}
