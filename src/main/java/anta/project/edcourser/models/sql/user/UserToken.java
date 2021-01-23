package anta.project.edcourser.models.sql.user;

import anta.project.edcourser.models.sql.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "token")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserToken extends BaseEntity {
    @OneToOne(mappedBy = "userToken")
    private User user;

    private String token;
}
