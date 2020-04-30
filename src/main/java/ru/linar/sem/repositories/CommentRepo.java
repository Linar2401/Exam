package ru.linar.sem.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.linar.sem.models.BookChapter;
import ru.linar.sem.models.Comment;

import java.util.List;
import org.springframework.data.domain.Page;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByChapter(BookChapter chapter, Pageable pageable);
    Integer countAllByChapter(BookChapter chapter);
}
