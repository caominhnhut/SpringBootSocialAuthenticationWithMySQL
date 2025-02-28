package com.sts.repository;

import com.sts.entity.RoleEntity;
import com.sts.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    @Query("SELECT r FROM UserRoleEntity ur JOIN RoleEntity r ON ur.roleId = r.id WHERE ur.userId = ?1")
    Set<RoleEntity> findByUserId(Long userId);

}
