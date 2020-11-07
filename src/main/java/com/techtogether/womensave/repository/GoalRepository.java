package com.techtogether.womensave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtogether.womensave.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Integer> {

}
