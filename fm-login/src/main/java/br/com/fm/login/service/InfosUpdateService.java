package br.com.fm.login.service;

import br.com.fm.login.dto.InfosUpdate.UpdateUserRequest;
import br.com.fm.mongodb.entity.UserEntity;
import br.com.fm.mongodb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class InfosUpdateService {

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<?> updateUser(UpdateUserRequest request) {


        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent()) {
            user.get().setName(request.getName());
            userRepository.save(user.get());
        }
        throw new IllegalArgumentException("User not found!");

    }


}
