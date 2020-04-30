package ru.linar.sem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.linar.sem.dto.BookDto;
import ru.linar.sem.dto.forms.BookForm;
import ru.linar.sem.dto.forms.ChapterForm;
import ru.linar.sem.dto.forms.CommentForm;
import ru.linar.sem.dto.forms.SignUpForm;
import ru.linar.sem.models.Book;
import ru.linar.sem.models.BookChapter;
import ru.linar.sem.models.Comment;
import ru.linar.sem.models.User;
import ru.linar.sem.security.details.UserDetailsImpl;
import ru.linar.sem.services.BookService;
import ru.linar.sem.services.CommentService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class BookController {
    private final Integer SIZE = 20;
    @Autowired
    private BookService bookService;

    @Autowired
    CommentService commentService;

    @PreAuthorize("permitAll()")
    @GetMapping("/book/view/{book-id}")
    public String book(Model map, @PathVariable("book-id") Long id, Authentication authentication){
        BookDto bookDto = bookService.getBook(id);
        map.addAttribute("book", bookDto.getData());
        map.addAttribute("chapters", bookDto.getChapters());
        if(authentication != null){
            userSetup(authentication, map);
        }
        return "book/view";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/book/view/{book-id}/{chapter-id}")
    public String bookChapter(Model map, @PathVariable("book-id") Book book, @PathVariable("chapter-id") BookChapter bookChapter, @RequestParam("next")Optional<Boolean> next, Authentication authentication){
        BookChapter chapter;
        if (next.isPresent()){
            Long id =  bookService.getChapterbyChapterId(book, bookChapter, next.get());
            return "redirect:/book/view/" + book.getId() + "/" + id;
        }
        else {
            chapter = bookChapter;
        };
        Boolean lastChapter = Integer.valueOf(chapter.getSerialNumber()).equals(book.getLastBookNumber());
        Boolean firstChapter = Integer.valueOf(chapter.getSerialNumber()).equals(book.getFirstBookNumber());
        map.addAttribute("comments", commentService.getCommentByBookChapter(chapter.getId(), 0, SIZE));
        if (commentService.getCountOfComments(chapter.getId())/SIZE == 0){
            map.addAttribute("nextComment", false);
        }
        else {
            map.addAttribute("nextComment", true);
        }

        map.addAttribute("chapter", chapter);
        map.addAttribute("last", lastChapter);
        map.addAttribute("first", firstChapter);
        map.addAttribute("form", new CommentForm());
        if(authentication != null){
            userSetup(authentication, map);
        }
        return "book/chapter";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/add")
    public String addComment(@Validated @ModelAttribute("form") CommentForm form, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            commentService.addComment(form);
        }
        return "redirect:/book/view/" + form.getBookId() + '/' + form.getChapterId();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/book/add")
    public String getBookForm(Model model, Authentication authentication){
        userSetup(authentication, model);
        model.addAttribute("form", new BookForm());
        return "book/createBook";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/book/add")
    public String addBook(@Validated @ModelAttribute("form") BookForm form
            , BindingResult bindingResult
            , Model model
            , Authentication authentication){
        if (bindingResult.hasErrors()){
            model.addAttribute("form", form);
            return "book/createBook";
        }
        bookService.addBook(form, ((UserDetailsImpl) authentication.getPrincipal()).getUser());
        return "redirect:/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/book/update/{book-id}")
    public String updateBookForm(Model model, Authentication authentication,@PathVariable("book-id") Book book){
        userSetup(authentication, model);
        model.addAttribute("form", new BookForm());
        model.addAttribute("book", book);
        model.addAttribute("chapters", bookService.getChapters(book));
        return "book/updateBook";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/book/update/{book-id}")
    public String updateBook(@Validated @ModelAttribute("form") BookForm form
            , BindingResult bindingResult
            , Model model, Authentication authentication
            , @PathVariable("book-id") Book book){
        if (bindingResult.hasErrors()){
            model.addAttribute("form", form);
            return "book/updateBook";
        }
        bookService.updateBook(book, form);
        return "redirect:/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/book/delete/{book-id}")
    public String deleteBookForm(Model model, Authentication authentication,@PathVariable("book-id") Book book){
        userSetup(authentication, model);
        model.addAttribute("book", book);
        return "book/deleteBook";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/book/delete/{book-id}")
    public String deleteBook(Model model, Authentication authentication, @PathVariable("book-id") Book book){
        bookService.deleteBook(book, ((UserDetailsImpl) authentication.getPrincipal()).getUser());
        return "redirect:/profile";
    }

    //    -----------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/book/addChapter/{book-id}")
    public String getBookChapterForm(Model model, Authentication authentication, @PathVariable("book-id") Book book){
        userSetup(authentication, model);
        model.addAttribute("form", new ChapterForm());
        model.addAttribute("book", book);
        return "book/chapterCreate";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/book/addChapter/{book-id}")
    public String addChapter(@Validated @ModelAttribute("form") ChapterForm form
            , @PathVariable("book-id") Book book
            , BindingResult bindingResult
            , Model model
            , Authentication authentication){
        if (bindingResult.hasErrors()){
            model.addAttribute("form", form);
            model.addAttribute("book", book);
            return "book/chapterCreate";
        }
        bookService.addBookChapter(form, book, ((UserDetailsImpl) authentication.getPrincipal()).getUser());
        return "redirect:/book/update/" + book.getId();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/book/update/{book-id}/{chapter-id}")
    public String updateBookChapterForm(Model model
            ,Authentication authentication
            ,@PathVariable("book-id") Book book
            ,@PathVariable("chapter-id") BookChapter chapter){
        userSetup(authentication, model);
        model.addAttribute("form", new ChapterForm());
        model.addAttribute("chapter", chapter);
        return "book/updateChapter";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/book/update/{book-id}/{chapter-id}")
    public String updateBookChapter(@Validated @ModelAttribute("form") ChapterForm form
            , BindingResult bindingResult
            , Model model
            , Authentication authentication
            , @PathVariable("book-id") Book book
            ,@PathVariable("chapter-id") BookChapter chapter){
        if (bindingResult.hasErrors()){
            model.addAttribute("form", form);
            model.addAttribute("book", book);
            model.addAttribute("chapter", chapter);
            return "book/updateChapter";
        }
        bookService.updateBookChapter(form, book, chapter);
        return "redirect:/book/update/" + book.getId();
    }
//
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/book/delete/{book-id}/{chapter-id}")
    public String deleteBookChapterForm(Model model
            , Authentication authentication
            ,@PathVariable("book-id") Book book
            ,@PathVariable("chapter-id")BookChapter chapter){
        userSetup(authentication, model);
        model.addAttribute("book", book);
        model.addAttribute("chapter", chapter);
        return "book/deleteChapter";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/book/delete/{book-id}/{chapter-id}")
    public String deleteChapterBook(Model model
            , Authentication authentication
            , @PathVariable("book-id") Book book
            ,@PathVariable("chapter-id")BookChapter chapter){
        bookService.deleteBookChapter(book,chapter ,((UserDetailsImpl) authentication.getPrincipal()).getUser());
        return "redirect:/book/update/" + book.getId();
    }

    private User userSetup(Authentication authentication, Model model){
        if (authentication.isAuthenticated()){
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();
            model.addAttribute("user", user);
            return user;
        }
        return new User();
    }
}
