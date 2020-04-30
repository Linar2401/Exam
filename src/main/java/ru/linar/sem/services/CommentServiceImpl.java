package ru.linar.sem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.linar.sem.dto.ChapterDto;
import ru.linar.sem.dto.forms.CommentForm;
import ru.linar.sem.exceptions.ChapterNotFoundException;
import ru.linar.sem.models.Book;
import ru.linar.sem.models.BookChapter;
import ru.linar.sem.models.Comment;
import ru.linar.sem.models.User;
import ru.linar.sem.repositories.BookChapterRepo;
import ru.linar.sem.repositories.BookRepo;
import ru.linar.sem.repositories.CommentRepo;
import ru.linar.sem.repositories.UserRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepo repo;

    @Autowired
    BookChapterRepo chapterRepo;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Override
    public List<Comment> getCommentByBookChapter(Long id, Integer page, Integer size) {
        BookChapter chapter = chapterRepo.getOne(id);
        if (chapter == null){
            log.error("Chapter with id" + id + "not exist in book with id" + id);
            throw new ChapterNotFoundException("Chapter with id" + id + "not exist in book with id" + id);
        }
        Sort sort = Sort.by("createdAt");
        Pageable request = PageRequest.of(page, size);
        return repo.findAllByChapter(chapter, request);
    }

    public Integer getCountOfComments(Long id){
        BookChapter chapter = chapterRepo.getOne(id);
        if (chapter == null){
            log.error("Chapter with id" + id + "not exist in book with id" + id);
            throw new ChapterNotFoundException("Chapter with id" + id + "not exist in book with id" + id);
        }
        return repo.countAllByChapter(chapter);
    }

    @Override
    public void addComment(CommentForm form) {
        User user = userService.getUser(form.getUserId());
        BookChapter chapter = bookService.getChapter(form.getBookId(), form.getChapterId());
        repo.save(Comment.builder().chapter(chapter).user(user).text(form.getText()).createdAt(LocalDateTime.now()).build());
    }
}
