package com.example.login;

import com.example.login.domain.User;
import com.example.login.repository.UserRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringUserRepository extends UserRepository, CrudRepository<User, Long> {

    List<User> findAll();

    Optional<User> findByEmail(String email);

}
