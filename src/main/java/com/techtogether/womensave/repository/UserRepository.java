package com.techtogether.womensave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtogether.womensave.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
