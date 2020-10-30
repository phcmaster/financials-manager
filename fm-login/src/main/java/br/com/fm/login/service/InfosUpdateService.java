package br.com.fm.login.service;

import br.com.fm.login.dto.InfosUpdate.UpdateUserRequest;
import br.com.fm.login.utils.JwtUtils;
import br.com.fm.mongodb.entity.UserEntity;
import br.com.fm.mongodb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class InfosUpdateService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;


    public void updateUser(UpdateUserRequest request) {

        Optional<UserEntity> user = userRepository.findById(jwtUtils.getSession().getId());

        if (user.isPresent()) {
           user.get().setName(request.getName());
           user.get().setEmail(request.getEmail());
            userRepository.save(user.get());
        }else {
          throw new IllegalArgumentException("User not found!");
        }

    }


}
