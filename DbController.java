package com.cable.practise.controller;

import com.cable.practise.entity.Employee;
import com.cable.practise.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DbController {


    @Autowired
    private DbService dbService;

    @PostMapping("/createEmployee")
    public ResponseEntity<String> createEmployees(@RequestBody List<Employee> employees) {
        dbService.createEmployees(employees);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employees created");
    }

    @GetMapping(path = "/getEmployees")
    public List<Employee> getEmployees(){
        return dbService.getEmployees();
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<String> updateEmployees(@RequestBody List<Employee> employees) {
        try {
            dbService.updateEmployees(employees);
            return ResponseEntity.ok("Employees updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    //the below delete request takes the eid from the user and
    // checks if the id is either present or not,
    // if present then it deletes the record of that id and shows the message "Record with ID 1002 deleted successfully!!!"
    //if the id provided is not present then shows the message "Record with ID 1005 not found"
    @DeleteMapping(path = "/deleteEmployee")
    public ResponseEntity<String> deleteEmployee(@RequestBody Employee employee) {
        int eid = employee.getEid();
        boolean isDeleted = dbService.deleteEmployee(eid);

        if (isDeleted) {
            return ResponseEntity.ok("Record with ID " + eid + " deleted successfully!!!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Record with ID " + eid + " not found");
        }
    }

}

