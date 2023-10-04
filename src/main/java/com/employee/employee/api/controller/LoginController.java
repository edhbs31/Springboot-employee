package com.employee.employee.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee.api.model.Login;
import com.employee.employee.api.model.Message;
import com.employee.employee.api.model.User;
import com.employee.employee.api.service.CryptoService;
import com.employee.employee.api.service.LoginService;
@RestController
public class LoginController {
    private LoginService loginService;
    private CryptoService cryptoService;
    public LoginController(LoginService loginService,  CryptoService cryptoService) {
        this.loginService = loginService;
        this.cryptoService = cryptoService;
    }

    @PostMapping("/login")
    public ResponseEntity<Message> getUser(@RequestBody Login login) {
        User userData = loginService.GetUser(login.getEmail());
        Message result = new Message(null, false);
        if(userData== null) {
            result.setMessage("account not found");
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);    
        }
        Boolean comparePassword = cryptoService.ComparePassword(login.getPassword(), userData.getPassword());
        if (comparePassword == false) {
            result.setMessage("wrong password");
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);    
        }
        result.setStatus(true);
        result.setMessage("successfully login");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
