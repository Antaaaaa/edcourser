package anta.project.edcourser.models.sql.user;

import anta.project.edcourser.models.sql.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_verification_info")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserVerificationInfo extends BaseEntity {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic_name")
    private String patronymicName;
    @Column(name = "day_of_birth")
    private byte dayOfBirth;
    @Column(name = "month_of_birth")
    private String monthOfBirth;
    @Column(name = "year_of_birth")
    private short yearOfBirth;
    @Column(name = "city")
    private String city;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "first_document")
    private String firstDocument;
    @Column(name = "second_document")
    private String secondDocument;
}
