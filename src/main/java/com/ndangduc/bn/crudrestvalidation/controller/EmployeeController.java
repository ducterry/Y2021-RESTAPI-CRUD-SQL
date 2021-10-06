package com.ndangduc.bn.crudrestvalidation.controller;

import com.ndangduc.bn.crudrestvalidation.entity.Employee;
import com.ndangduc.bn.crudrestvalidation.exception.ResourceNotFoundException;
import com.ndangduc.bn.crudrestvalidation.mapper.EmpMapper;
import com.ndangduc.bn.crudrestvalidation.model.request.CreateEmployeeReq;
import com.ndangduc.bn.crudrestvalidation.model.request.UpdateEmployeeReq;
import com.ndangduc.bn.crudrestvalidation.repository.EmployeeRepository;
import com.ndangduc.bn.crudrestvalidation.service.EmployeeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Employee Controller", description = "API Employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = this.employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody CreateEmployeeReq createEmployeeReq) {
       return  this.employeeService.createEmployee(createEmployeeReq);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody UpdateEmployeeReq updateEmployeeReq) throws ResourceNotFoundException {
        Employee updateEmployee = this.employeeService.updateEmployee(employeeId, updateEmployeeReq);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
