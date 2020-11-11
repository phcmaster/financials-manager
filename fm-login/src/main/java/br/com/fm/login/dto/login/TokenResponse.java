package br.com.fm.login.dto.login;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

    private String token;
    private String type;
    private LocalDateTime dateTime;


}
