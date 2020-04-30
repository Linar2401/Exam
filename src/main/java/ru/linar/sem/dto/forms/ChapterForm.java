package ru.linar.sem.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChapterForm {
    @NotEmpty(message = "Name should be not empty")
    private String name;

    @NotEmpty(message = "Text should be not empty")
    private String text;
}
