package com.ndangduc.bn.crudrestvalidation.repository;

import com.ndangduc.bn.crudrestvalidation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
