package com.ra.ss3s.controller;

import com.ra.ss3s.model.entity.Employee;
import com.ra.ss3s.repository.EmployeeRepository;
import com.ra.ss3s.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-add";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        employee.setCreatedAt(LocalDateTime.now());
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping
    public String listEmployees(@RequestParam(defaultValue = "") String phone,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "id") String sortBy,
                                Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Employee> employeePage = phone.isEmpty()
                ? employeeService.getAllEmployees(pageable)
                : employeeService.searchByPhone(phone, pageable);

        model.addAttribute("employeePage", employeePage);
        model.addAttribute("phone", phone);
        return "employee-list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Employee employee = employeeRepository.findById(id.longValue()).orElseThrow();
        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Integer id, @ModelAttribute Employee updated) {
        Employee employee = employeeRepository.findById(id.longValue()).orElseThrow();
        employee.setName(updated.getName());
        employee.setEmail(updated.getEmail());
        employee.setPhoneNumber(updated.getPhoneNumber());
        employee.setSalary(updated.getSalary());
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeRepository.deleteById(id.longValue());
        return "redirect:/employees";
    }
}