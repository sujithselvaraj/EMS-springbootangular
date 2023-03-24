//package com.sujith.app.service.impl;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.sujith.app.exception.ResourceNotFoundException;
//import com.sujith.app.model.Employee;
//import com.sujith.app.repository.EmployeeRepository;
//import com.sujith.app.service.EmployeeService;
//
//
//@Service
//public class EmployeeServiceImpl implements EmployeeService
//{
//
//	private EmployeeRepository employeeRepository;
//	
//	
//	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//		super();
//		this.employeeRepository = employeeRepository;
//	}
//
//
//	@Override
//	public Employee saveEmployee(Employee employee) {
//		// TODO Auto-generated method stub
//		return employeeRepository.save(employee);
//	}
//
//
//	@Override
//	public List<Employee> getAllEmployees() {
//		// TODO Auto-generated method stub
//		return employeeRepository.findAll();
//	}
//
//
//	@Override
//	public Employee getEmployeeById(long id) {
//		// TODO Auto-generated method stub
//		Optional<Employee> employee=employeeRepository.findById( id);
//		if(employee.isPresent())
//		{
//			return employee.get();
//		}
//		else
//		{
//			throw new ResourceNotFoundException("Employee","Id", id);
//		}
//	}
//
//
//	@Override
//	public Employee updateEmployee(Employee employee, long id) {
//
//		//check wheather the employee with given id is existing or not
//		Employee existingEmployee=employeeRepository.findById(id).orElseThrow(
//				()->new ResourceNotFoundException("Employee", "Id", id));
//		
//		existingEmployee.setFirstname(employee.getFirstname());
//		existingEmployee.setLastname(employee.getLastname());
//		existingEmployee.setEmail(employee.getEmail());
//		
//		//save existing employee to DB
//		
//		employeeRepository.save(existingEmployee);
//				
//		
//		return existingEmployee;
//	}
//
//
//	@Override
//	public void deleteEmployee(long id) {
//		
//		//check wheathr the employee is existing or not
//		employeeRepository.findById(id).orElseThrow(()->
//		new ResourceNotFoundException("Employee", "Id", id));
//
//		employeeRepository.deleteById(id);
//	}
//	
//}
