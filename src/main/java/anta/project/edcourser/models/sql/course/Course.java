package anta.project.edcourser.models.sql.course;


import anta.project.edcourser.models.sql.BaseEntity;
import anta.project.edcourser.models.sql.user.User;
import anta.project.edcourser.models.sql.user.UserVerificationInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Course extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "is_locked", columnDefinition = "boolean default true")
    boolean isLocked;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_info", referencedColumnName = "id")
    CourseInfo courseInfo;

    @OneToMany(mappedBy = "course")
    Set<CourseVideo> courseVideo;

    @ManyToMany(mappedBy = "courses")
    Set<User> usersOwners;
}
