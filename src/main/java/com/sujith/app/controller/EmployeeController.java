package com.sujith.app.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sujith.app.exception.ResourceNotFoundException;
//import com.sujith.app.model.Employee;
//import com.sujith.app.repository.EmployeeRepository;
//
//@CrossOrigin(origins="http://localhost:4200")
//@RestController
//@RequestMapping("/api/v1/")
//public class EmployeeController 
//{
//	
////	private EmployeeService employeeService;
////
////	public EmployeeController(EmployeeService employeeService) {
////		super();
////		this.employeeService = employeeService;
////	}
////	
//	//build create employee rest api
////	@PostMapping
////	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
////	{
////		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.CREATED );
////	}
////	
////	//build all employees rest api
////	@GetMapping
////	
////	public List<Employee> getAllEmployees()
////	 {
////		 return employeeService.getAllEmployees();
////		 
////	 }
////	
////	
////	//build get employee by id rest api
////	//http://localhost:8080/api/employees/1
////	@GetMapping("{id}")
////	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId)
////	{
////		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.CREATED);
////	}
////	
////	
////	//build update employee rest api
////	//http://localhost:8080/api/employees/1
////	@PutMapping("/{id}")
////	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id 
////			,@RequestBody Employee employee)
////	{
////		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
////	}
////	
////	
////	//build delete employee rest api
////	//http://localhost:8080/api/employees/1
////	@DeleteMapping("{id}")
////	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id)
////	{
////		//delete employee from DB
////		employeeService.deleteEmployee(id);
////		
////		return new ResponseEntity<String>("Employee Deleted Sucessfully", HttpStatus.OK);
////	}
//	
//	
//	

//	
//}



import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.sujith.app.exception.ResourceNotFoundException;
import com.sujith.app.model.Employee;
import com.sujith.app.repository.EmployeeRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}		
	
	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}
	
	// update employee rest api
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	


	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
