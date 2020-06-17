package com.wayqui.bootcamel.routes.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class EmployeeResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String salary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate;
    private Integer age;
    private String photo;

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary='" + salary + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", photo='" + photo + '\'' +
                '}';
    }
}
