package com.sts.model.user.dto;

import com.sts.entity.UserStatus;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private String password;

    private UserStatus status;

}
