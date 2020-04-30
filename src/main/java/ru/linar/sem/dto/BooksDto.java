package ru.linar.sem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.linar.sem.models.Book;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BooksDto {
    private List<Book> data;
}

