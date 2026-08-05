package com.leaves.leaves.controller;

import com.leaves.leaves.entity.Leave;
import com.leaves.leaves.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaves")
@CrossOrigin("*")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @PostMapping
    public Leave saveLeave(@RequestBody Leave leave) {
        leave.setStatus("Pending");
        return leaveService.saveLeave(leave);
    }

    @DeleteMapping("/{id}")
    public String deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return "Leave Deleted Successfully";
    }

    @PutMapping("/{id}")
    public Leave updateLeave(@PathVariable Long id,
                             @RequestBody Leave leave) {
        return leaveService.updateLeave(id, leave);
    }

    // ==========================
    // Admin Dashboard Counts
    // ==========================

    @GetMapping("/count/pending")
    public long getPendingCount() {
        return leaveService.getLeaveCountByStatus("Pending");
    }

    @GetMapping("/count/approved")
    public long getApprovedCount() {
        return leaveService.getLeaveCountByStatus("Approved");
    }

    @GetMapping("/count/rejected")
    public long getRejectedCount() {
        return leaveService.getLeaveCountByStatus("Rejected");
    }

    // ==========================
    // Employee Dashboard
    // ==========================

    @GetMapping("/employee/{employeeName}")
    public List<Leave> getEmployeeLeaves(@PathVariable String employeeName) {
        return leaveService.getLeavesByEmployee(employeeName);
    }

    @GetMapping("/employee/{employeeName}/count")
    public long getMyLeaveCount(@PathVariable String employeeName) {
        return leaveService.getMyLeaveCount(employeeName);
    }

    @GetMapping("/employee/{employeeName}/pending")
    public long getMyPendingCount(@PathVariable String employeeName) {
        return leaveService.getMyPendingCount(employeeName);
    }

    @GetMapping("/employee/{employeeName}/approved")
    public long getMyApprovedCount(@PathVariable String employeeName) {
        return leaveService.getMyApprovedCount(employeeName);
    }

    @GetMapping("/employee/{employeeName}/rejected")
    public long getMyRejectedCount(@PathVariable String employeeName) {
        return leaveService.getMyRejectedCount(employeeName);
    }

}