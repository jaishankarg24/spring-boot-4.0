package com.example.demo.controllers;

import com.example.demo.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage() {
//        return "Secret message: asdfal@#$DASD";
//    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return new EmployeeDTO(id, "Jai", "jai@gmail.com", 27, LocalDate.of(2024, 1, 2), true);
    }

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                  @RequestParam(required = false) String sortBy) {
        return "Hi age "+age+" "+sortBy;
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
        inputEmployee.setId(100L);
        return inputEmployee;
    }

    @PutMapping String updateEmployeeById() {
        return "Hello from Put";
    }

}
