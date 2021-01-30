package anta.project.edcourser.models.sql.user;

import anta.project.edcourser.enums.UserRole;
import anta.project.edcourser.models.sql.BaseEntity;
import anta.project.edcourser.models.sql.course.Course;
import anta.project.edcourser.models.sql.course.CourseVideo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_config_id", referencedColumnName = "id")
    UserConfig userConfig;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id", referencedColumnName = "id")
    UserInfo userInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_token_id", referencedColumnName = "id")
    UserToken userToken;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_verification_info_id", referencedColumnName = "id")
    UserVerificationInfo userVerificationInfo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_course",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "course_id")}
    )
    Set<Course> courses;
}
