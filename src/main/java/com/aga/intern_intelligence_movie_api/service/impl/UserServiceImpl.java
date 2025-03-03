package com.aga.intern_intelligence_movie_api.service.impl;

import com.aga.intern_intelligence_movie_api.entity.PasswordResetToken;
import com.aga.intern_intelligence_movie_api.entity.Role;
import com.aga.intern_intelligence_movie_api.entity.User;
import com.aga.intern_intelligence_movie_api.exception.InvalidOldPasswordException;
import com.aga.intern_intelligence_movie_api.exception.NotFoundException;
import com.aga.intern_intelligence_movie_api.exception.UsernameAlreadyExistsException;
import com.aga.intern_intelligence_movie_api.mapper.UserMapper;
import com.aga.intern_intelligence_movie_api.model.UserDto;
import com.aga.intern_intelligence_movie_api.repository.PasswordResetTokenRepository;
import com.aga.intern_intelligence_movie_api.repository.UserRepository;
import com.aga.intern_intelligence_movie_api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public void register(UserDto userDto, Boolean isAdmin) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UsernameAlreadyExistsException("USERNAME_ALREADY_EXISTS");
        }
        var entity = userMapper.toUserEntity(userDto);
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        entity.setRoles(getRolesForUser(entity, isAdmin));
        userRepository.save(entity);
    }

    private List<Role> getRolesForUser(User entity, boolean isAdmin) {
        var roles = new ArrayList<Role>();

        if (isAdmin) {
            roles.add(Role.builder().name("ROLE_ADMIN").user(entity).build());
        } else {
            roles.add(Role.builder().name("ROLE_USER").user(entity).build());
        }
        return roles;
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword, HttpServletRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("USERNAME_NOT_FOUND"));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new InvalidOldPasswordException("OLD_PASSWORD_NOT_MATCH");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        if (token != null && !token.isEmpty()) {
            PasswordResetToken blacklistedToken = new PasswordResetToken();
            blacklistedToken.setToken(token);
            passwordResetTokenRepository.save(blacklistedToken);
        }
        SecurityContextHolder.clearContext();
    }
}
