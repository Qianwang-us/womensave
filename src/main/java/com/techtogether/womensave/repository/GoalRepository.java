package com.techtogether.womensave.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtogether.womensave.model.Goal;
import com.techtogether.womensave.model.User;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
	Optional<Goal> findByYearAndUser(int year, User user);
}
