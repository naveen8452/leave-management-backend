package com.leaves.leaves.service;

import com.leaves.leaves.entity.Leave;
import com.leaves.leaves.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    // ==========================
    // Admin Dashboard
    // ==========================

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public Leave saveLeave(Leave leave) {
        return leaveRepository.save(leave);
    }

    public void deleteLeave(Long id) {
        leaveRepository.deleteById(id);
    }

    public Leave updateLeave(Long id, Leave leave) {

        Leave existingLeave = leaveRepository.findById(id).orElse(null);

        if (existingLeave != null) {

            existingLeave.setEmployeeName(leave.getEmployeeName());
            existingLeave.setLeaveType(leave.getLeaveType());
            existingLeave.setFromDate(leave.getFromDate());
            existingLeave.setToDate(leave.getToDate());
            existingLeave.setStatus(leave.getStatus());

            return leaveRepository.save(existingLeave);
        }

        return null;
    }

    public long getLeaveCountByStatus(String status) {
        return leaveRepository.countByStatus(status);
    }

    // ==========================
    // Employee Dashboard
    // ==========================

    public List<Leave> getLeavesByEmployee(String employeeName) {
        return leaveRepository.findByEmployeeName(employeeName);
    }

    public long getMyLeaveCount(String employeeName) {
        return leaveRepository.countByEmployeeName(employeeName);
    }

    public long getMyPendingCount(String employeeName) {
        return leaveRepository.countByEmployeeNameAndStatus(employeeName, "Pending");
    }

    public long getMyApprovedCount(String employeeName) {
        return leaveRepository.countByEmployeeNameAndStatus(employeeName, "Approved");
    }

    public long getMyRejectedCount(String employeeName) {
        return leaveRepository.countByEmployeeNameAndStatus(employeeName, "Rejected");
    }

}