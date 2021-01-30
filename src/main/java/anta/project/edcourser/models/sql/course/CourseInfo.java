package anta.project.edcourser.models.sql.course;


import anta.project.edcourser.models.sql.BaseEntity;
import anta.project.edcourser.models.sql.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class CourseInfo extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "shortVideo")
    private String videoUrl;

    @OneToOne(mappedBy = "courseInfo")
    private Course course;

}
