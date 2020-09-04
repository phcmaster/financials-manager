package br.com.fm.mongodb.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;



@Setter
@Getter
@Document(collection = "user")
public class UserEntity implements Serializable {

    @Id
    private Integer id;

    @Field
    private String email;

    @Field
    private String password;


}
