package com.wipro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
