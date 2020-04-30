package ru.linar.sem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.linar.sem.models.Book;
import ru.linar.sem.models.BookChapter;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Book data;
    private Map<Long, String> chapters;
}
