package com.aga.intern_intelligence_movie_api.service.impl;


import com.aga.intern_intelligence_movie_api.entity.Role;
import com.aga.intern_intelligence_movie_api.repository.UserRepository;
import com.aga.intern_intelligence_movie_api.service.SecurityService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found: " + username);
                });

        return new User(userEntity.getUsername(), userEntity.getPassword(), getAuthorities(userEntity.getRoles()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}
