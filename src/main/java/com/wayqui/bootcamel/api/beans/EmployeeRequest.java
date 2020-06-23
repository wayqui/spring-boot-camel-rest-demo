package com.wayqui.bootcamel.api.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private String salary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;
    private String photo;

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary='" + salary + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
