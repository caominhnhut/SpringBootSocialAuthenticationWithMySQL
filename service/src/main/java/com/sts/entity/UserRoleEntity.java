package com.sts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "user_roles")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleEntity extends AuditMetaData implements Serializable {

    @Serial
    private static final long serialVersionUID = 327894678329L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

}
