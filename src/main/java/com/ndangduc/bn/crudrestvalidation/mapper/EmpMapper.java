package com.ndangduc.bn.crudrestvalidation.mapper;

import com.ndangduc.bn.crudrestvalidation.entity.Employee;
import com.ndangduc.bn.crudrestvalidation.model.request.CreateEmployeeReq;
import com.ndangduc.bn.crudrestvalidation.model.request.UpdateEmployeeReq;

public class EmpMapper {
    public static Employee createToEmpEntity(CreateEmployeeReq req) {
        Employee entity = new Employee();
        entity.setFirstName(req.getFirstName());
        entity.setLastName(req.getLastName());
        entity.setEmailId(req.getEmailId());
        entity.setPassportNumber(req.getPassportNumber());

        return entity;
    }


    public static Employee updateToEmpEntity(Employee entity, UpdateEmployeeReq req) {
        entity.setFirstName(req.getFirstName());
        entity.setLastName(req.getLastName());
        entity.setEmailId(req.getEmailId());
        entity.setPassportNumber(req.getPassportNumber());

        return entity;
    }
}
