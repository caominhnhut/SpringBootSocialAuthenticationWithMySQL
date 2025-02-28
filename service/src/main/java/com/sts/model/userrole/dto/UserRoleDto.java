package com.sts.model.userrole.dto;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleDto {

    private Long id;

    private Long userId;

    private Long roleId;

}
