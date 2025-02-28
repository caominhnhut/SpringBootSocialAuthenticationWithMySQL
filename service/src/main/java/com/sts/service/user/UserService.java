package com.sts.service.user;

import com.sts.model.user.dto.UserDto;
import com.sts.model.user.request.UserCreateRequest;
import com.sts.model.user.request.UserUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(Long id);

    UserDto createUser(UserCreateRequest userRequest);

    UserDto updateUser(Long id, UserUpdateRequest userDetails);

    void deleteUser(Long id);

}
