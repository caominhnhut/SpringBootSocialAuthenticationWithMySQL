package com.sts.resource;

import com.sts.model.userrole.dto.UserRoleDto;
import com.sts.model.userrole.request.UserRoleCreateRequest;
import com.sts.model.userrole.request.UserRoleUpdateRequest;
import com.sts.service.userrole.UserRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@RequiredArgsConstructor
@Slf4j
public class UserRoleResource {

    private final UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
        return ResponseEntity.ok(userRoleService.getAllUserRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDto> getUserRoleById(@PathVariable Long id) {
        return userRoleService.getUserRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserRoleDto> createUserRole(@RequestBody @Valid UserRoleCreateRequest userRoleRequest) {
        return ResponseEntity.ok(userRoleService.createUserRole(userRoleRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleDto> updateUserRole(@PathVariable Long id, @RequestBody @Valid UserRoleUpdateRequest userRoleDetails) {
        return ResponseEntity.ok(userRoleService.updateUserRole(id, userRoleDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long id) {
        userRoleService.deleteUserRole(id);
        return ResponseEntity.noContent().build();
    }

}
