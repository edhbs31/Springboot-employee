package com.employee.employee.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.employee.api.model.User;
import com.employee.employee.api.repository.EmployeeRepository;

@Service
public class LoginService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public User GetUser(String email) {
        return employeeRepository.findOneByEmail(email);
    }
}
