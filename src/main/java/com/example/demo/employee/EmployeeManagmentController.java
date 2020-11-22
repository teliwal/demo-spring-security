package com.example.demo.employee;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("managment/api/employees")
public class EmployeeManagmentController {

    private static final List<Employee> employees = List.of(
            new Employee(1,"telly"),
            new Employee(2,"hajar"),
            new Employee(3,"kilosa"),
            new Employee(4,"mourad")
    );

    @GetMapping
    @PreAuthorize("hasAuthority('employee:read')")
    public List<Employee> listing(){
        return employees;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('employee:hire')")
    public void hire(@RequestBody Employee employee){
        System.out.println("Hiring "+ employee);
    }

    @PutMapping("/{idEmployee}")
    @PreAuthorize("hasAuthority('employee:hire')")
    public void raiseSalary(@PathVariable("idEmployee") int idEmployee){
        System.out.println("Raising my hardworking employee "+ idEmployee);
    }

    @DeleteMapping("/{idEmployee}")
    @PreAuthorize("hasAuthority('employee:fire')")
    public void fire(@PathVariable("idEmployee") int idEmployee){
        System.out.println("Firing the lazy one "+idEmployee);
    }
}
