package com.wipro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.dto.EmployeeRequest;
import com.wipro.entity.Employee;
import com.wipro.exception.EmployeeNotFoundException;
import com.wipro.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee create(EmployeeRequest req) {
        Employee emp = new Employee();
        emp.setName(req.getName());
        emp.setEmail(req.getEmail());
        emp.setDepartment(req.getDepartment());
        return repository.save(emp);
    }

    public List<Employee> list() {
        return repository.findAll();
    }

    public Employee get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee update(Long id, EmployeeRequest req) {
        Employee existing = get(id);
        existing.setName(req.getName());
        existing.setEmail(req.getEmail());
        existing.setDepartment(req.getDepartment());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Employee existing = get(id);
        repository.delete(existing);
    }
}
