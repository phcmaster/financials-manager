package br.com.fm.login.service;

import br.com.fm.login.dto.NewUserRequest;
import br.com.fm.mongodb.entity.UserEntity;
import br.com.fm.mongodb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.fm.login.utils.BCryptPasswordEncoderUtil.passwordEncoder;

@Service
@Slf4j
public class RegisterUserService {

//    @Autowired
//    private UserMapper mapper;

    @Autowired
    private UserRepository userRepository;


    public void registerUser(NewUserRequest request) {

        userValidation(request);
        var passwordEncoder = passwordEncoder(request.getPassword());

        UserEntity entity = new UserEntity();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPassword(passwordEncoder);

        userRepository.save(entity);
        log.info("Register User - user created with success.");

    }


    public void userValidation(NewUserRequest request) {

        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent()) {
            log.error("User Validation - The User already exist!");
            throw new IllegalArgumentException("The User already exist!");

        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            log.error("User Validation - The passwords do not match!");
            throw new IllegalArgumentException("The passwords do not match!");
        }

    }


}
