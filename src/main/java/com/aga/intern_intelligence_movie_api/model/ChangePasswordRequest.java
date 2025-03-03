package com.aga.intern_intelligence_movie_api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
