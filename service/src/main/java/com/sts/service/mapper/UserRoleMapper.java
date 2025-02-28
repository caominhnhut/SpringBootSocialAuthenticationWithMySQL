package com.sts.service.mapper;

import com.sts.entity.UserRoleEntity;
import com.sts.model.userrole.dto.UserRoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

    UserRoleDto toDto(UserRoleEntity userRoleEntity);

}
