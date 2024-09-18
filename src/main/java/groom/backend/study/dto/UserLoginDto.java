package groom.backend.study.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserLoginDto {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    public UserLoginDto(String mail, String password) {
    }
}
