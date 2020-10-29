package br.com.fm.login.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    @Email(message = "Invalid email!")
    @JsonProperty("email")
    private String email;

    @Min(value = 6)
    @JsonProperty("password")
    private String password;

}
