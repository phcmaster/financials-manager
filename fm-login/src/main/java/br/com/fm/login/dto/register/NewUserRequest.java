package br.com.fm.login.dto.register;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class NewUserRequest {


    @JsonProperty("name")
    @NotNull
    private String name;

    @Email
    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("password")
    private String password;

    @NotNull
    @JsonProperty("confirmPassword")
    private String confirmPassword;

    @NotNull
    @JsonProperty("role")
    private List<String> role;





}
