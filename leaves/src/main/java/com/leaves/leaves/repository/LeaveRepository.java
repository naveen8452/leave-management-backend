package com.leaves.leaves.repository;

import com.leaves.leaves.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    // Admin Dashboard
    long countByStatus(String status);

    // Employee Dashboard
    List<Leave> findByEmployeeName(String employeeName);

    long countByEmployeeName(String employeeName);

    long countByEmployeeNameAndStatus(String employeeName, String status);

}