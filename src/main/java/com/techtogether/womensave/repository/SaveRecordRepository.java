package com.techtogether.womensave.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtogether.womensave.model.SaveRecord;
import com.techtogether.womensave.model.User;

public interface SaveRecordRepository extends JpaRepository<SaveRecord, Integer> {
	public List<SaveRecord> findByYearAndUser(int year, User user);
	public Optional<SaveRecord> findByYearAndMonthAndUser(int year, int month, User user);
}
