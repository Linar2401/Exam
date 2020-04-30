package ru.linar.sem.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.linar.sem.utils.FieldMatch;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldMatch.List({
        @FieldMatch(first = "password", second = "passwordRepeat", message = "The password are not equals")
})
public class SignUpForm {
    @NotNull
    @NotEmpty(message = "Email should be not empty")
    private String nickname;
    @NotNull
    @NotEmpty(message = "Email should be not empty")
    @Email(message = "Email must be relevant")
    private String email;
    @NotNull
    @NotEmpty(message = "Email should be not empty")
    private String password;
    @NotNull
    @NotEmpty(message = "Email should be not empty")
    private String passwordRepeat;
    @NotNull
    @NotEmpty(message = "Email should be not empty")
    private String country;
    private boolean gender;
}
