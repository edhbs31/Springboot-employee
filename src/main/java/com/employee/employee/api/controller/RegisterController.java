package com.employee.employee.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee.api.model.Message;
import com.employee.employee.api.model.User;
import com.employee.employee.api.service.RegisterService;
import com.employee.employee.api.service.CryptoService;
@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    private CryptoService cryptoService;
    public RegisterController(RegisterService registerService, CryptoService cryptoService) {
        this.registerService = registerService;
        this.cryptoService = cryptoService;
    }

    @PostMapping("/register")
    public ResponseEntity<Message> getUser(@RequestBody  User user) {
        Message result = new Message(null, false);
        String decryptedPassword = cryptoService.EncryptPassword(user.getPassword());
        user.setPassword(decryptedPassword);
        User isInsert = registerService.InsertUser(user);
        if(isInsert == null) {
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);    
        }
        result.setMessage("success create");
        result.setStatus(true);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
