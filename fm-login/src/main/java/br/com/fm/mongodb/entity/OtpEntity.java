package br.com.fm.mongodb.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "otp")
public class OtpEntity {

    @Id
    private String id;

    @Field
    private String otp;

    @Field
    private String userId;

    @Field
    private String email;

    @Field
    private boolean status;

    @Field
    private LocalDateTime creation;
}
