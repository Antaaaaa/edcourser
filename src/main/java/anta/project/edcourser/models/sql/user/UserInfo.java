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
@Table(name = "user_info")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserInfo extends BaseEntity {

    @OneToOne(mappedBy = "userInfo")
    private User user;

    @Column(name = "name")
    private String name;
    @Column(name = "nickName")
    private String nickName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "country")
    private String country;

}
