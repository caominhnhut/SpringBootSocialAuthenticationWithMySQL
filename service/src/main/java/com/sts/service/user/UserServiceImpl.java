package com.sts.service.user;

import com.sts.entity.UserEntity;
import com.sts.entity.UserStatus;
import com.sts.model.user.dto.UserDto;
import com.sts.model.user.request.UserCreateRequest;
import com.sts.model.user.request.UserUpdateRequest;
import com.sts.repository.UserRepository;
import com.sts.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    @Override
    public UserDto createUser(UserCreateRequest userRequest) {
        var userEntity = UserEntity.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .status(UserStatus.ACTIVE)
                .build();
        var savedUser = userRepository.save(userEntity);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long id, UserUpdateRequest userDetails) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    var updatedUser = UserEntity.builder()
                            .id(existingUser.getId())
                            .name(userDetails.getName())
                            .email(existingUser.getEmail())
                            .password(existingUser.getPassword())
                            .status(UserStatus.ACTIVE)
                            .build();
                    return userMapper.toDto(userRepository.save(updatedUser));
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
