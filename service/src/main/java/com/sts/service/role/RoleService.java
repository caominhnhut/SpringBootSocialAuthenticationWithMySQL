package com.sts.service.role;

import com.sts.model.role.dto.RoleDto;
import com.sts.model.role.request.RoleCreateRequest;
import com.sts.model.role.request.RoleUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<RoleDto> getAllRoles();

    Optional<RoleDto> getRoleById(Long id);

    RoleDto createRole(RoleCreateRequest role);

    RoleDto updateRole(Long id, RoleUpdateRequest roleDetails);

    void deleteRole(Long id);

}
