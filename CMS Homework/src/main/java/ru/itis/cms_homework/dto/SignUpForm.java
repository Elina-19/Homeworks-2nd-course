package ru.itis.cms_homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpForm {

    @NotBlank(message = "Enter the username")
    private String username;

    @NotBlank(message = "Enter the email")
    private String email;

    @NotBlank(message = "Enter the password")
    @Size(min = 5, max = 15, message = "Password should have length from {min} to {max}")
    private String password;
}
