package com.techtogether.womensave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtogether.womensave.model.SaveRecord;

public interface SaveRecordRepository extends JpaRepository<SaveRecord, Integer> {

}
