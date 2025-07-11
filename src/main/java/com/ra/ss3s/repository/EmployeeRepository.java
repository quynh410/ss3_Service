package com.ra.ss3s.repository;

import com.ra.ss3s.model.dto.EDTO;
import com.ra.ss3s.model.entity.Employee;
import com.ra.ss3s.model.entity.EmployeeInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByPhoneNumber(String phone);

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findEmployeeBySalary(@Param("salary") double salary);

    @Query("SELECT new com.ra.ss3s.model.dto.EDTO(e.id, e.name, e.salary, e.phoneNumber) FROM Employee e")
    List<EDTO> findAllEmployeeDTO();

    @Query("SELECT e FROM Employee e")
    List<EmployeeInfo> getAllEmployeeInfo();

    Page<Employee> findByPhoneNumberContaining(String phoneNumber, Pageable pageable);
}