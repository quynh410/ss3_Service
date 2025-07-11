package com.ra.ss3s.service;

import com.ra.ss3s.model.entity.Employee;
import com.ra.ss3s.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Page<Employee> searchByPhone(String phone, Pageable pageable) {
        return employeeRepository.findByPhoneNumberContaining(phone, pageable);
    }

    public Employee findById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id.longValue());
        return employee.orElse(null);
    }

    public void save(Employee employee) {
        if (employee.getCreatedAt() == null) {
            employee.setCreatedAt(LocalDateTime.now());
        }
        employeeRepository.save(employee);
    }

    public void update(Employee employee) {
        employeeRepository.save(employee);
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id.longValue());
    }
}