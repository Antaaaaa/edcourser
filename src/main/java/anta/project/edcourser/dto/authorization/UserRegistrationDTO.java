package anta.project.edcourser.dto.authorization;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String email;
    private String password;
    private String name;
    private String nickName;
    private String phone;
    private String country;
}
