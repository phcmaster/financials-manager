package br.com.fm.login.service;

import br.com.fm.login.dto.enums.ProfileEnum;
import br.com.fm.login.dto.register.newUserRequest;
import br.com.fm.login.mapper.UserMapper;
import br.com.fm.mongodb.entity.ProfileEntity;
import br.com.fm.mongodb.entity.UserEntity;
import br.com.fm.mongodb.repository.ProfileRepository;
import br.com.fm.mongodb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.fm.login.utils.BCryptPasswordEncoderUtil.passwordEncoder;

@Service
@Slf4j
public class RegisterUserService {

    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;


    public void registerUser(newUserRequest request) {


        List<ProfileEntity> profileEntities = userValidation(request);
        var passwordEncoder = passwordEncoder(request.getPassword());
        UserEntity userEntity = mapper.requestToEntity(request, profileEntities,  passwordEncoder);

        userRepository.save(userEntity);
        log.info("Register User - user created with success.");
    }


    private List<ProfileEntity> userValidation(newUserRequest request) {

        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent()) {
            log.error("User Validation - The user already exist!");
            throw new IllegalArgumentException("The user already exist!");

        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            log.error("User Validation - The passwords do not match!");
            throw new IllegalArgumentException("The passwords do not match!");
        }

        List<String> strProfiles = request.getRole();

        List<ProfileEntity> roles = new ArrayList<>();

        if (strProfiles == null) {
            ProfileEntity userRole = profileRepository.findByRole(ProfileEnum.USER.getValue())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strProfiles.forEach(profile -> {
                if (profile.equalsIgnoreCase("admin")) {
                    ProfileEntity adminRole = profileRepository.findByRole(ProfileEnum.ADMIN.getValue())
                            .orElseThrow(() -> new RuntimeException("Role Admin not found."));
                    roles.add(adminRole);
                } else {
                    ProfileEntity userRole = profileRepository.findByRole(ProfileEnum.USER.getValue())
                            .orElseThrow(() -> new RuntimeException("Role User not found."));
                    roles.add(userRole);
                }
            });
        }

        return roles;
    }



}
