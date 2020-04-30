package ru.linar.sem.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInForm {
    @NotEmpty(message = "Email should be not empty")
    @Email(message = "Email must be relevant")
    private String email;

    @NotEmpty(message = "Password should be not empty")
    private String password;
}
