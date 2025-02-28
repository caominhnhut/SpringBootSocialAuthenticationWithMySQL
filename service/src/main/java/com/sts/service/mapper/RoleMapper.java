package com.sts.service.mapper;

import com.sts.entity.RoleEntity;
import com.sts.model.role.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(RoleEntity roleEntity);

}
