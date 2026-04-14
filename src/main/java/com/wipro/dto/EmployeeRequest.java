package com.wipro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EmployeeRequest {

	@NotBlank(message = "name must not be blank")
    private String name;

    @Pattern(regexp = ".+@.+", message = "email must contain @")
    private String email;

    private String department;

    public String getName() { return name; }
    public void setName(String name) { this.name = name.trim(); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email.trim(); }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department != null ? department.trim() : ""; }

}
