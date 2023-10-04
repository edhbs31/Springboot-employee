package com.employee.employee.api.model;

import jakarta.persistence.Column;

public class Login {
    @Column( nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
