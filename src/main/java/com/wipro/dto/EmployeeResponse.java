package com.wipro.dto;

import com.wipro.entity.Employee;

public class EmployeeResponse {
	private final Employee employee;

    public EmployeeResponse() {
        this.employee = new Employee();
    }

    public EmployeeResponse(Long id, String name, String email, String department) {
        this.employee = new Employee(id, name, email, department);
    }

    public Long getId() { return this.employee.getId(); }
    public String getName() { return this.employee.getName(); }
    public String getEmail() { return this.employee.getEmail(); }
    public String getDepartment() { return this.employee.getDepartment(); }

    public void setId(Long id) { this.employee.setId(id); }
    public void setName(String name) { this.employee.setName(name); }
    public void setEmail(String email) { this.employee.setEmail(email); }
    public void setDepartment(String department) { this.employee.setDepartment(department); }
}
