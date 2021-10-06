package com.ndangduc.bn.crudrestvalidation.controller;

import com.ndangduc.bn.crudrestvalidation.entity.Employee;
import com.ndangduc.bn.crudrestvalidation.exception.ResourceNotFoundException;
import com.ndangduc.bn.crudrestvalidation.mapper.EmpMapper;
import com.ndangduc.bn.crudrestvalidation.model.request.CreateEmployeeReq;
import com.ndangduc.bn.crudrestvalidation.model.request.UpdateEmployeeReq;
import com.ndangduc.bn.crudrestvalidation.repository.EmployeeRepository;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody CreateEmployeeReq createEmployeeReq) {
        try {
            return employeeRepository.save(EmpMapper.createToEmpEntity(createEmployeeReq));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody UpdateEmployeeReq updateEmployeeReq) throws ResourceNotFoundException {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));


        final Employee updatedEmployee = employeeRepository.save(EmpMapper.updateToEmpEntity(employee,updateEmployeeReq));
        return ResponseEntity.ok(updatedEmployee);
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
