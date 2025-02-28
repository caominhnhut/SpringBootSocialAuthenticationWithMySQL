package com.sts.service.mapper;

import com.sts.entity.UserEntity;
import com.sts.model.user.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);

}
