package com.sts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleEntity extends AuditMetaData implements Serializable {

    @Serial
    private static final long serialVersionUID = 12342343L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

}
