package ru.linar.sem.services;

import ru.linar.sem.dto.BookDto;
import ru.linar.sem.dto.BooksDto;
import ru.linar.sem.dto.ChapterDto;
import ru.linar.sem.dto.forms.BookForm;
import ru.linar.sem.dto.forms.ChapterForm;
import ru.linar.sem.models.Book;
import ru.linar.sem.models.BookChapter;
import ru.linar.sem.models.User;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book> getBooks(Integer page);
    BooksDto getBooksByAuthor(Long userId);
    BookDto getBook(Long id);
    BookChapter getChapter(Long bookId, Long id);
    Long getChapterbyChapterId(Book book, BookChapter bookChapter, boolean next);
    Integer getCount();
    void addBook(BookForm form, User user);
    void updateBook(Book book, BookForm form);
    void deleteBook(Book book, User user);
    Map<Long, String> getChapters(Book book);
    void addBookChapter(ChapterForm form,Book book, User user);
    void updateBookChapter(ChapterForm form,Book book, BookChapter chapter);
    void deleteBookChapter(Book book, BookChapter chapter, User user);
}
