package br.com.fm.investment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private String id;

    private String name;

    private String userName;

    private List<String> authorities;

}
