package com.ndangduc.bn.crudrestvalidation.service;

import com.ndangduc.bn.crudrestvalidation.entity.Employee;
import com.ndangduc.bn.crudrestvalidation.exception.ResourceNotFoundException;
import com.ndangduc.bn.crudrestvalidation.mapper.EmpMapper;
import com.ndangduc.bn.crudrestvalidation.model.request.CreateEmployeeReq;
import com.ndangduc.bn.crudrestvalidation.model.request.UpdateEmployeeReq;
import com.ndangduc.bn.crudrestvalidation.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployee() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException {
        return this.employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
    }

    @Override
    public Employee createEmployee(CreateEmployeeReq createEmployeeReq) {
        try {
            return employeeRepository.save(EmpMapper.createToEmpEntity(createEmployeeReq));
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Long employeeId, UpdateEmployeeReq updateEmployeeReq) throws ResourceNotFoundException {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        final Employee updatedEmployee = employeeRepository.save(EmpMapper.updateToEmpEntity(employee, updateEmployeeReq));
        return updatedEmployee;
    }
}
