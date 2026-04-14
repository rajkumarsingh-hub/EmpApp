package com.wipro.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.wipro.dto.EmployeeRequest;
import com.wipro.dto.EmployeeResponse;
import com.wipro.entity.Employee;
import com.wipro.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(@Valid @RequestBody EmployeeRequest req) {
        Employee emp = service.create(req);
        return toResponse(emp);
    }

    @GetMapping
    public List<EmployeeResponse> list() {
        return service.list().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public EmployeeResponse get(@PathVariable Long id) {
        return toResponse(service.get(id));
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest req) {
        return toResponse(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    private EmployeeResponse toResponse(Employee emp) {
        return new EmployeeResponse(emp.getId(), emp.getName(), emp.getEmail(), emp.getDepartment());
    }
}
