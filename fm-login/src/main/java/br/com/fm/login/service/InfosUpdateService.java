package br.com.fm.login.service;

import br.com.fm.login.dto.InfosUpdate.PasswordUpdateRequest;
import br.com.fm.login.dto.InfosUpdate.UpdateUserRequest;
import br.com.fm.login.dto.InfosUpdate.UserOtpRequest;
import br.com.fm.login.exception.CouldNotSendNotificationException;
import br.com.fm.login.service.feign.FeignClientNotification;
import br.com.fm.login.utils.JwtUtils;
import br.com.fm.mongodb.entity.OtpEntity;
import br.com.fm.mongodb.entity.UserEntity;
import br.com.fm.mongodb.repository.OtpRepository;
import br.com.fm.mongodb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.fm.login.utils.BCryptPasswordEncoderUtil.passwordEncoder;

@Service
@Slf4j
public class InfosUpdateService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;


    @Autowired
    private FeignClientNotification notification;


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


    public void changePassword(PasswordUpdateRequest request){

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            log.error("User Validation - The passwords do not match!");
            throw new IllegalArgumentException("The passwords do not match!");
        }

        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent()) {
            user.get().setPassword(passwordEncoder(request.getPassword()));
            userRepository.save(user.get());
        }

    }

    public void forgotPassword(String email){

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        otpGeneration(user);
    }



    public boolean otpValidation(String otp, String email){

        Optional<OtpEntity> userOtp = otpRepository.findByEmail(email);

        if(userOtp.get().getOtp().equals(otp)){
            userOtp.get().setStatus(true);
            otpRepository.save(userOtp.get());

        }else{
            throw new IllegalArgumentException("OTP is not valid!");
        }

        return true;

    }


    private void otpGeneration(UserEntity user){
        int max = 10000;
        int min= 1000;

        int randomOtp = (int)(Math.random() * (max - min + 1) + min);

        OtpEntity otpEntity = new OtpEntity();

        otpEntity.setCreation(LocalDateTime.now());
        otpEntity.setOtp(String.valueOf(randomOtp));
        otpEntity.setUserId(user.getId());
        otpEntity.setEmail(user.getEmail());
        otpRepository.save(otpEntity);
        log.info("OTP Generated.");

        UserOtpRequest userOtpRequest = new UserOtpRequest();
        userOtpRequest.setEmail(user.getEmail());
        userOtpRequest.setName(user.getName());

        try{
            notification.sendEmailNotification(String.valueOf(randomOtp), userOtpRequest);
            log.info("Email was sended.");

        }catch (Exception e){
            throw new CouldNotSendNotificationException(e.getMessage());
        }

    }

}
