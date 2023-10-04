package com.employee.employee.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CryptoService  {
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;
    public CryptoService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public String EncryptPassword(String pw) {
        String encodedPassword = passwordEncoder.encode(pw);
        return encodedPassword;
    }
    
    public boolean ComparePassword(String passwordOri, String passwordDB) {
        return passwordEncoder.matches(passwordOri, passwordDB);
    }
}
