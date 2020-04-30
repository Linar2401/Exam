package ru.linar.sem.dto.forms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentForm {
    @NotNull
    private Long chapterId;

    @NotNull
    private Long bookId;

    @NotNull
    private Long userId;

    @NotEmpty(message = "Message should be not empty")
    private String text;
}
