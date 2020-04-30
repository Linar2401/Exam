package ru.linar.sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.linar.sem.models.Book;
import ru.linar.sem.models.User;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    public List<Book> findAllByAuthorsContains(User user);
}
