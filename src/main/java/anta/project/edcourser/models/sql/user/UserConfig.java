package anta.project.edcourser.models.sql.user;

import anta.project.edcourser.enums.UserVerificationStatus;
import anta.project.edcourser.models.sql.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_config")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserConfig extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_verification_status")
    @Enumerated(EnumType.STRING)
    private UserVerificationStatus verificationStatus;

}
