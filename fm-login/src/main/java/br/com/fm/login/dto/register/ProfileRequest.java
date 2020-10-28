package br.com.fm.login.dto.register;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProfileRequest implements Serializable {

    @JsonProperty(value = "roleUser")
    private String role;

}
