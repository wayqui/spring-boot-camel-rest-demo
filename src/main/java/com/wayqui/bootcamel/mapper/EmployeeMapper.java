package com.wayqui.bootcamel.mapper;

import com.wayqui.bootcamel.dto.EmployeeDto;
import com.wayqui.bootcamel.routes.beans.EmployeeRequest;
import com.wayqui.bootcamel.routes.beans.EmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );

    EmployeeDto requestToDto(EmployeeRequest employeeRequest);
    EmployeeResponse dtoToResponse(EmployeeDto employeeDto);
    List<EmployeeResponse> dtosToResponses(List<EmployeeDto> employeeDtos);
}
