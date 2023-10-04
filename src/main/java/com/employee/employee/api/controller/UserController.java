package com.employee.employee.api.controller;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee.api.model.Message;
import com.employee.employee.api.model.UserAge;
import com.employee.employee.api.model.UserUpdate;
import com.employee.employee.api.repository.UserProjection;
import com.employee.employee.api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.type.TypeReference;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<Page<UserProjection>> getUser(@RequestParam  (defaultValue = "10") int limit, @RequestParam (defaultValue = "1") int page, @RequestParam (defaultValue = "") String  query) {
        Page<UserProjection> user = userService.GetUser(limit, page, query);
        return new ResponseEntity<>(user, HttpStatus.OK);    
    }
     @GetMapping("/userById/{id}")
    public ResponseEntity<UserProjection> getUserById(@PathVariable Long id) {
        UserProjection user = userService.findById(id);
        if (user!=null) {
            return new ResponseEntity<>(user, HttpStatus.OK);    
        }
        return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);    
    }
    @GetMapping("/userByAge")
    public ResponseEntity<List<Integer>> getUserAll(@RequestParam("data") String serializedData) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserAge> userList;
        try {
            userList = objectMapper.readValue(serializedData, new TypeReference<List<UserAge>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<Integer> user = userService.GetCountPerAge(userList);
        return new ResponseEntity<>(user, HttpStatus.OK);    
    }
    @PutMapping("/updateUser")
    public ResponseEntity<Message> updateUser(@RequestBody UserUpdate payload) {
        Message result = new Message(null, false);
        boolean update = userService.UpdateUser(payload);
        if (update) {
            result.setStatus(true);
            result.setMessage("success update");
            return new ResponseEntity<>(result, HttpStatus.OK);    
        }
        result.setMessage("failed update");
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);    
    }
      @DeleteMapping("/deleteUser/{id}")
        public ResponseEntity<Message> deleteUser(@PathVariable Long id) {
        Message result = new Message(null, false);
        UserProjection user = userService.findById(id);
        if (user==null) {
            result.setMessage("not found");
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);    
        }
        boolean delete = userService.DeleteById(id);
        if (delete == false) {
            result.setMessage("failed delete");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);    
        }
        result.setStatus(true);
        result.setMessage("success delete");
        return new ResponseEntity<>(result, HttpStatus.OK);    
        
    }
}
