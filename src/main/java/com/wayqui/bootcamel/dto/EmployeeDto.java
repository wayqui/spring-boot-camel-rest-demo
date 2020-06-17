package com.wayqui.bootcamel.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class EmployeeDto {

    private String id;
    private String firstName;
    private String lastName;
    private String salary;
    private LocalDate birthDate;
    @Getter(AccessLevel.NONE)
    private Integer age;
    private String photo;

    public Integer getAge() {
        if (this.getBirthDate() != null)
            this.age = Period.between(this.getBirthDate(), LocalDate.now()).getYears();
        return this.age;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
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
