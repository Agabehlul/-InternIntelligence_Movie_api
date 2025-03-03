package com.aga.intern_intelligence_movie_api.service;

import com.aga.intern_intelligence_movie_api.model.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    void register(UserDto userDto, Boolean isAdmin);

    void changePassword(String username, String oldPassword, String newPassword, HttpServletRequest request);
}
