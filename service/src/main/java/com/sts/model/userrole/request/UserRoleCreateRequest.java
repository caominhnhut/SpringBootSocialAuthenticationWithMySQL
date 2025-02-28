package com.sts.model.userrole.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRoleCreateRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long roleId;

}