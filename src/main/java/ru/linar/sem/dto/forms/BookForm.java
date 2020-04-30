package ru.linar.sem.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookForm {
    @NotEmpty(message = "Name should be not empty")
    private String name;

    @NotEmpty(message = "Description should be not empty")
    private String description;
}
