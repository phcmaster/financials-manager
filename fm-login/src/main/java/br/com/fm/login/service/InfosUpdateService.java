package br.com.fm.login.service;

import br.com.fm.login.dto.InfosUpdate.PasswordUpdateRequest;
import br.com.fm.login.dto.InfosUpdate.UpdateUserRequest;

public interface InfosUpdateService {

    void updateUser(UpdateUserRequest request);

    void changePassword(PasswordUpdateRequest request);

    void forgotPassword(String email);

    boolean otpValidation(String otp, String email);
}
