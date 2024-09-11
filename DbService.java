package com.cable.practise.services;

import com.cable.practise.entity.Employee;
import com.cable.practise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Creating the record
    public void createEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            if (employeeRepository.existsById(employee.getEid())) {
                throw new RuntimeException("Employee with ID " + employee.getEid() + " already exists");
            }
        }
        employeeRepository.saveAll(employees);
    }

    // Reading the records
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    // Updating the existing records
    public void updateEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            if (employeeRepository.existsById(employee.getEid())) {
                employeeRepository.save(employee);
            } else {
                throw new RuntimeException("Employee with ID " + employee.getEid() + " not found");
            }
        }
    }

    // Deleting the records of the table
    public boolean deleteEmployee(int employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return true;
        } else {
            return false;
        }
    }
}
