package com.sujith.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sujith.app.model.Employee;



//Spring data JPA internally providees @repository and @transactional annotation so we no need to add it
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> 
{
	

}
