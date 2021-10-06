package com.ndangduc.bn.crudrestvalidation.service;

import com.ndangduc.bn.crudrestvalidation.entity.Employee;
import com.ndangduc.bn.crudrestvalidation.exception.ResourceNotFoundException;
import com.ndangduc.bn.crudrestvalidation.model.request.CreateEmployeeReq;
import com.ndangduc.bn.crudrestvalidation.model.request.UpdateEmployeeReq;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();

    Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException;

    Employee createEmployee(CreateEmployeeReq createEmployeeReq);

    Employee updateEmployee(Long employeeId, UpdateEmployeeReq updateEmployeeReq) throws ResourceNotFoundException;
}
