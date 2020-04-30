package ru.linar.sem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.linar.sem.models.Book;
import ru.linar.sem.models.Comment;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsDto {
    private List<Comment> comments;
    private Boolean nextComment;
}
