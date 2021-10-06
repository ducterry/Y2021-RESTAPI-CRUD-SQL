package com.ndangduc.bn.crudrestvalidation.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateEmployeeReq {
    @NotNull
    @Size(min = 2, message = "First Name should have at least 2 characters")
    @ApiModelProperty(example = "Đức")
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last Name should have at least 2 characters")
    @ApiModelProperty(example = "Nguyễn Đăng")
    private String lastName;

    @Email
    @NotBlank
    @ApiModelProperty(example = "ndangduc.bn@gmail.com")
    private String emailId;

    @NotNull
    @Size(min = 2, message = "Passport should have at least 2 characters")
    @ApiModelProperty(example = "123456789")
    private String passportNumber;
}
