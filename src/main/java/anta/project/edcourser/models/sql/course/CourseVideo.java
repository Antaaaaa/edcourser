package anta.project.edcourser.models.sql.course;

import anta.project.edcourser.models.sql.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "course_video")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class CourseVideo extends BaseEntity {

    @Column(name = "video_name")
    String videoName;

    @Column(name = "video_preview")
    String videoPreview;

    @Column(name = "video_url")
    String videoUrl;

    @Column(name = "video_number")
    Long videoNumber;

    @Column(name = "video_is_locked", columnDefinition = "boolean default true")
    boolean isLocked;

    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course course;
}
