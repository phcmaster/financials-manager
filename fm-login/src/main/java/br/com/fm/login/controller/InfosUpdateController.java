package br.com.fm.login.controller;


import br.com.fm.login.dto.UpdateUserRequest;
import br.com.fm.login.service.InfosUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/informations")
public class InfosUpdateController {

    @Autowired
    private InfosUpdateService updateService;


    @PutMapping("/update")
    public ResponseEntity<?> informationUpdate(@Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(updateService.updateUser(request));

    }


}
