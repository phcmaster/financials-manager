package br.com.fm.login.service.feign;

import br.com.fm.login.dto.InfosUpdate.UserOtpRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "clientNotification", url = "${email.notification.url}")
public interface FeignClientNotification {

    @PostMapping(value = "/notification/send-email")
    String sendEmailNotification(@RequestParam(name = "otp") String otp, @RequestBody UserOtpRequest request);

}
