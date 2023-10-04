package com.employee.employee.api.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.employee.employee.api.model.UserAge;
import com.employee.employee.api.model.UserUpdate;
import com.employee.employee.api.repository.EmployeeRepository;
import com.employee.employee.api.repository.UserProjection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EntityManager entityManager; // Inject the EntityManager
    public Page<UserProjection> GetUser(int limit, int page, String query) {
        page = page-1;
        PageRequest pageable = PageRequest.of(page, limit);
        if(query.isEmpty()) {
            return employeeRepository.findUsersWithoutPassword(pageable);
        }
        return employeeRepository.findUsersWithoutPasswordWithLike(pageable, query);
    }
    public UserProjection findById(Long id) {
        return employeeRepository.findByIdWithoutPassword(id);
    }
    @Transactional
    public boolean UpdateUser(UserUpdate payload) {
        Query query = entityManager.createQuery("UPDATE User SET age = :age, username = :username, email = :email WHERE id = :id");
        query.setParameter("age", payload.getAge());
        query.setParameter("username", payload.getUsername());
        query.setParameter("email", payload.getEmail());
        query.setParameter("id", payload.getId());
        int updatedCount = query.executeUpdate();
        if (updatedCount == 1) {
            return true;
        }
        return false;
    }
    public List<Integer>GetCountPerAge(List<UserAge> data) {
        List<Integer> integerList = new ArrayList<>();
        Integer result =0;
        for (int i = 0; i < data.size(); i++) {
            UserAge userList2 = data.get(i);
            Integer start = userList2.getStart();
            Integer end = userList2.getEnd();
            if (i == data.size() - 1) {
                result = employeeRepository.findByAgeLast(end);
            } else {
                result = employeeRepository.findByAge(start, end);
            }
            
            integerList.add(result);
        }
        
        return integerList;

    }
    public boolean DeleteById(Long id) {
        employeeRepository.deleteById(id);
        return true;
    }
    
}
