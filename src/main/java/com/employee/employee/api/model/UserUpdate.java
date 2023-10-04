package com.employee.employee.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cas_employee")

public class UserUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="email", nullable = false, unique = true)
    private String email;
    @Column(name="username",  nullable = false)
    private String username;
    @Column(name="age" )
    private int age;

    public UserUpdate() {}

    public  UserUpdate(int id, String email, String username, int age) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.username = username;
    }
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
}
