package br.com.fm.mongodb.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "profile")
public class ProfileEntity implements GrantedAuthority {

    @Id
    private String id;

    @Field
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
