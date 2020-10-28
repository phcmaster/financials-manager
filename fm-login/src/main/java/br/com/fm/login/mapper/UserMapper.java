package br.com.fm.login.mapper;

import br.com.fm.login.dto.register.NewUserRequest;
import br.com.fm.mongodb.entity.ProfileEntity;
import br.com.fm.mongodb.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {

    @Mapping(target = "profiles", source = "roles")
    @Mapping(target = "password", source = "password")
    UserEntity requestToEntity(NewUserRequest request, List<ProfileEntity> roles, String password);

}
