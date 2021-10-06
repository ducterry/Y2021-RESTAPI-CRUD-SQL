package com.ndangduc.bn.crudrestvalidation.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 2, message = "First Name should have at least 2 characters")
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last Name should have at least 2 characters")
    private String lastName;

    @Email
    @NotBlank
    private String emailId;

    @NotNull
    @Size(min = 2, message = "Passport should have at least 2 characters")
    private String passportNumber;
}