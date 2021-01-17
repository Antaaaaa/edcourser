package anta.project.edcourser.models.sql.user;

import anta.project.edcourser.enums.UserRole;
import anta.project.edcourser.models.sql.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class User extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
