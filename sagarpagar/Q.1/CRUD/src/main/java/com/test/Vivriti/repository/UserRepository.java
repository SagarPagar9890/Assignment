package com.test.Vivriti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.Vivriti.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
