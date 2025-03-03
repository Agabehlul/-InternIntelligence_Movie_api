package com.aga.intern_intelligence_movie_api.mapper;

import com.aga.intern_intelligence_movie_api.entity.User;
import com.aga.intern_intelligence_movie_api.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserEntity(UserDto userDto);
}
