package com.BookMyShow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    @NotBlank(message = "First name is mandatory")
    private String fName;

    @NotBlank(message = "lname is mandatory")
    private String lName;

    @NotNull(message = "Please Enter Dob")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @NotBlank(message = "Email is mandatory ")
    @Email(message = "Please provide valid email address")
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",message =
//            "Password must contain at least 8 characters, one digit, one lowercase letter, one uppercase letter, and one special character..")
    private String email;
    @NotBlank(message = "Please enter valid Password")
    @Size(min= 8,message = "Password should be eight characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
