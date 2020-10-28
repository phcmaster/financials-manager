package br.com.fm.login.dto.InfosUpdate;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    @JsonProperty("name")
    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    @JsonProperty("email")
    @NotNull
    @NotBlank
    @NotEmpty
    private String email;

    @JsonProperty("password")
    @Transient
    private String password;

}
