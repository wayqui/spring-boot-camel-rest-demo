package com.wayqui.bootcamel.services;

import com.wayqui.bootcamel.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public static List<EmployeeDto> employees = new ArrayList<>();

    @Override
    public EmployeeDto getEmployee(String id) {
        return employees
                .stream()
                .filter(employeeDto -> employeeDto.getId().equals(id))
                .findFirst()
                .orElse(new EmployeeDto());
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employees;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        employeeDto.setId(UUID.randomUUID().toString());
        employees.add(employeeDto);
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(String id, EmployeeDto updatedEmployee) {
        EmployeeDto employeeDto = getEmployee(id);
        employeeDto.setBirthDate(updatedEmployee.getBirthDate());
        employeeDto.setFirstName(updatedEmployee.getFirstName());
        employeeDto.setLastName(updatedEmployee.getLastName());
        employeeDto.setPhoto(updatedEmployee.getPhoto());
        employeeDto.setSalary(updatedEmployee.getSalary());

        return employeeDto;
    }

    @Override
    public void deleteEmployee(String id) {
        employees.removeIf(employeeDto -> employeeDto.getId().equals(id));
    }
}
