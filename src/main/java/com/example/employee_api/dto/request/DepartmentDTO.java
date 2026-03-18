package com.example.employee_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.*;
@Data
public class DepartmentDTO {
    @NotBlank(message = "ten phong ban khong duoc de trong")
    @Size(min = 5,max = 50,
    message = "Ten phai tu 5-50 ki tu")
    private String name;
    @Size(max = 100, message = "Mo ta toi da 100 ki tu")
    private String description;
}
