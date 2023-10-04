package com.employee.employee.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.employee.api.model.User;
import com.employee.employee.api.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegisterService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public User InsertUser(User user) {
        return employeeRepository.save(user);
    }
}
