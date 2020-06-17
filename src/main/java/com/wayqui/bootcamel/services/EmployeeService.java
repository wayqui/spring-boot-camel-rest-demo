package com.wayqui.bootcamel.services;

import com.wayqui.bootcamel.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto getEmployee(String id);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(String id, EmployeeDto updatedEmployee);

    void deleteEmployee(String id);

}
