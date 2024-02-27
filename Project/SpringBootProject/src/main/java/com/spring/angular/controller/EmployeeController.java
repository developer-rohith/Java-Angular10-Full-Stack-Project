package com.spring.angular.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.exception.ResourceNotFoundException;
import com.spring.angular.model.Employee;
import com.spring.angular.repository.EmployeeRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeerepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeerepository.findAll();
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeerepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployee(@PathVariable("id") long id)
	{
		return employeerepository.findById(id);
	}
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable("id") long id ,@RequestBody Employee employeeDetails)
	{
		Employee e=employeerepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("employee with that id no exites"));
		e.setFirstName(employeeDetails.getFirstName());
		e.setLastName(employeeDetails.getLastName());
		e.setEmailId(employeeDetails.getEmailId());
		return employeerepository.save(e);
		}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable("id") long id)
	{
		Employee e=employeerepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("employee with that id no exites"));
		employeerepository.delete(e);
	Map<String, Boolean> m=new HashMap<>();
	m.put("Deleted", Boolean.TRUE);
	return ResponseEntity.ok(m);
		}

}
