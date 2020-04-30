package ru.linar.sem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linar.sem.dto.BookDto;
import ru.linar.sem.dto.BooksDto;
import ru.linar.sem.dto.ChapterDto;
import ru.linar.sem.dto.forms.BookForm;
import ru.linar.sem.dto.forms.ChapterForm;
import ru.linar.sem.exceptions.ChapterNotFoundException;
import ru.linar.sem.exceptions.UserNotFoundException;
import ru.linar.sem.models.Book;
import ru.linar.sem.models.BookChapter;
import ru.linar.sem.models.User;
import ru.linar.sem.repositories.BookChapterRepo;
import ru.linar.sem.repositories.BookRepo;
import ru.linar.sem.repositories.UserRepo;

import java.math.BigInteger;
import java.util.*;

@Service
@Slf4j
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepo repository;

    @Autowired
    BookChapterRepo chapterRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public List<Book> getBooks(Integer page) {
        Sort sort = Sort.by("name");
        PageRequest request = PageRequest.of(page, 20, sort);
        Page<Book> pageResult = repository.findAll(request);
        return pageResult.getContent();
    }

    @Override
    public BooksDto getBooksByAuthor(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        if (!user.isPresent()){
            log.error("User with id:" + userId + " is not exist");
            throw new UserNotFoundException("User with id:" + userId + " is not exist");
        }
        List<Book> books = repository.findAllByAuthorsContains(user.get());
        return BooksDto.builder().data(books).build();
    }

    @Override
    public BookDto getBook(Long id) {
        Optional<Book> optional = repository.findById(id);
        if (optional.isPresent()){
            Book book = optional.get();
            List<Object[]> chaptersList= chapterRepo.findAllByBook_Id(book.getId());
            Map<Long, String> result = new LinkedHashMap<>();
            for (int i = 0; i < chaptersList.size();i++){
                result.put(((BigInteger)chaptersList.get(i)[0]).longValue(), (String) chaptersList.get(i)[1]);
            }
            return BookDto.builder().data(book).chapters(result).build();
        }
        else {
            log.error("Book with id" + id + "not exist");
            throw new IllegalArgumentException("Book with id" + id + "not exist");
        }
    }



    @Override
    public BookChapter getChapter(Long bookId, Long id) {
        List<Object[]> chaptersList= chapterRepo.findAllByBook_Id(bookId);
        Map<Long, String> result = new LinkedHashMap<>();
        for (int i = 0; i < chaptersList.size();i++){
            result.put(((BigInteger)chaptersList.get(i)[0]).longValue(), (String) chaptersList.get(i)[1]);
        }
        if (!result.containsKey(id)){
            log.error("Chapter with id" + id + "not exist in book with id" + bookId);
            throw new ChapterNotFoundException("Chapter with id" + id + "not exist in book with id" + bookId);
        }
        return chapterRepo.getOne(id);
    }

    public Long getChapterbyChapterId(Book book, BookChapter chapter, boolean next) {
//        List<Object> chaptersList= next?chapterRepo.findAllByBook_IdMoreThan(chapter.getId(),chapter.getSerialNumber())
//                :chapterRepo.findAllByBook_IdLessThan(chapter.getId(),chapter.getSerialNumber());
        List<BookChapter> chapters = next?chapterRepo.findAllByBookAndSerialNumberGreaterThanOrderBySerialNumber(book, chapter.getSerialNumber())
                :chapterRepo.findAllByBookAndAndSerialNumberLessThanOrderBySerialNumberDesc(book, chapter.getSerialNumber());
        if (chapters == null){
            log.error("Chapter with id" + chapter.getId() + "not exist in book with id" + book.getId());
            throw new ChapterNotFoundException("Chapter with id" + chapter.getId() + "not exist in book with id" + book.getId());
        }
        return chapters.get(0).getId();
    }

    @Override
    public Integer getCount() {
        return (int) repository.count();
    }

    @Override
    public void addBook(BookForm form, User user) {
        List<User> list = new ArrayList<>();
        list.add(user);
        Book book = Book.builder().authors(list).name(form.getName()).description(form.getDescription()).chapterCounter(0).lastBookNumber(0).build();
        repository.save(book);
        user.getBooks().add(book);
        userRepo.save(user);
    }

    @Override
    public void updateBook(Book book, BookForm form) {
        book.setName(form.getName());
        book.setDescription(form.getDescription());
        repository.save(book);
    }

    @Override
    public void deleteBook(Book book, User user) {
        for (User author: book.getAuthors()){
            if (author.getId().equals(user.getId())){
                for (User a: book.getAuthors()){
                    a.getBooks().remove(book);
                    userRepo.save(a);
                }
            }
        }
        repository.delete(book);
    }

    @Override
    public Map<Long, String> getChapters(Book book) {
        List<Object[]> chaptersList= chapterRepo.findAllByBook_Id(book.getId());
        Map<Long, String> result = new LinkedHashMap<>();
        for (int i = 0; i < chaptersList.size();i++){
            result.put(((BigInteger)chaptersList.get(i)[0]).longValue(), (String) chaptersList.get(i)[1]);
        }
        return result;
    }

    @Override
    public void addBookChapter(ChapterForm form, Book book, User user) {
       for (User author:book.getAuthors()){
           if (author.getId().equals(user.getId())){
               BookChapter bookChapter = BookChapter.builder()
                       .book(book)
                       .name(form.getName())
                       .text(form.getText())
                       .serialNumber((byte)(book.getLastBookNumber()+1))
                       .build();
               chapterRepo.save(bookChapter);
               book.getChapters().add(bookChapter);
               book.setLastBookNumber(0+bookChapter.getSerialNumber());
               book.setChapterCounter(book.getChapterCounter()+1);
               repository.save(book);
               break;
           }
       }

    }

    @Override
    public void updateBookChapter(ChapterForm form, Book book, BookChapter chapter) {
        if (chapter.getBook().getId().equals(book.getId())){
            chapter.setName(form.getName());
            chapter.setText(form.getText());
            chapterRepo.save(chapter);
        }
    }

    @Override
    public void deleteBookChapter(Book book, BookChapter chapter, User user) {
        for (User author: book.getAuthors()){
            if (author.getId().equals(user.getId())){
                book = repository.getOne(book.getId());
                    for (BookChapter a: book.getChapters()){
                    if (a.getId().equals(chapter.getId()))
                        book.getChapters().remove(chapter);
                        book.setChapterCounter(book.getChapterCounter()-1);
                        if (book.getLastBookNumber()==((int)chapter.getSerialNumber())){
                            List<BookChapter> bc = chapterRepo.findAllBySerialNumberLessThanOrderBySerialNumberDesc(chapter.getSerialNumber());
                            book.setLastBookNumber(!bc.isEmpty()? 0+bc.get(0).getSerialNumber():0);
                        }
                        if (book.getFirstBookNumber()==((int)chapter.getSerialNumber())){
                            List<BookChapter> bc = chapterRepo.findAllBySerialNumberGreaterThanOrderBySerialNumber(chapter.getSerialNumber());
                            book.setLastBookNumber(!bc.isEmpty()? 0+bc.get(0).getSerialNumber():0);
                        }
                        repository.save(book);
                        chapterRepo.delete(chapter);
                        break;
                }
            }
        }
    }
}
