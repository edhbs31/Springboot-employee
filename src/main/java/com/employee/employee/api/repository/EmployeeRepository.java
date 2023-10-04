package com.employee.employee.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.employee.api.model.User;
public interface EmployeeRepository extends JpaRepository<User, Long> {
    User findOneByEmail(String email);
    @Query("SELECT u.id as id, u.username as username, u.email as email, u.age as age FROM User u")
    Page<UserProjection> findUsersWithoutPassword(PageRequest pageable);
    @Query("SELECT u.id as id, u.username as username, u.email as email, u.age as age FROM User u where LOWER(u.username) LIKE LOWER(concat('%', :query, '%')) or LOWER(u.email) LIKE LOWER(concat('%', :query, '%'))")
    Page<UserProjection> findUsersWithoutPasswordWithLike(PageRequest pageable, String query);
    @Query("SELECT u.id as id, u.username as username, u.email as email, u.age as age FROM User u where u.id = :id")
    UserProjection findByIdWithoutPassword(Long id);

    @Query("SELECT count(*) as age FROM User u where u.age >= :start and u.age <= :end")
    Integer findByAge(@Param("start") int age1, @Param("end") int age2);

    @Query("SELECT count(*) as age FROM User u where u.age >= :end")
    Integer findByAgeLast(@Param("end") int age1);

}

