package com.example.demo.employee;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/employees")
public class EmployeeController {

    private static final List<Employee> employees = List.of(
            new Employee(1,"telly"),
            new Employee(2,"hajar"),
            new Employee(3,"kilosa"),
            new Employee(4,"mourad")
    );

    @GetMapping
    public List<Employee> getAllEmployees(){
        return  employees;
    }

    @GetMapping(value = "/{id}")
    public Employee getEmployeeById(@PathVariable("id") int id){
        return employees
                .stream().filter(e -> e.getId() == id).findFirst()
                .orElseThrow( () -> new IllegalArgumentException("No Employee found with this id: " + id));
    }

}
