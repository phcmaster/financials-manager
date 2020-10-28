package br.com.fm.login.dto.enums;

import lombok.Getter;

@Getter
public enum ProfileEnum {

    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    ProfileEnum(String value) {
        this.value = value;
    }
}
