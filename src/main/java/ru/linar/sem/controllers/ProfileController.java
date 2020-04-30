package ru.linar.sem.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.linar.sem.dto.BooksDto;
import ru.linar.sem.dto.forms.SignInForm;
import ru.linar.sem.dto.forms.SignUpForm;
import ru.linar.sem.models.Book;
import ru.linar.sem.models.User;
import ru.linar.sem.security.details.UserDetailsImpl;
import ru.linar.sem.services.BookService;
import ru.linar.sem.services.CountryListService;
import ru.linar.sem.services.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class ProfileController {
    @Autowired
    CountryListService countryListService;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @GetMapping("/signIn")
    public String getSignInPage(ModelMap map) {
        map.put("form", new SignInForm());
        return "profile/sign_in";
    }

//    @PostMapping("/signIn")
//    public String postSignInPage(@Validated @ModelAttribute("form") SignInForm form, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("form", form);
//            System.err.println("ERROR COUNT: " + bindingResult.getErrorCount());
//            System.err.println("ERROR FIELD: " + bindingResult.getFieldError().getField());
//            System.err.println("ERROR CODE: " + bindingResult.getFieldError().getCode());
//            return "profile/sign_in";
//        }
//        log.info("Logining");
//        return "redirect:/";
//    }


    @PreAuthorize("isAnonymous()")
    @GetMapping("/signUp")
    public String getSignUpPage(ModelMap map) {
        try {
            Map<String ,String> list = countryListService.getList();
            map.put("list", list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("form", new SignUpForm());
        return "profile/sign_up";
    }


    @PostMapping("/signUp")
    public String signUp(@Validated @ModelAttribute("form") SignUpForm form, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("form", form);
            try {
                Map<String ,String> list = countryListService.getList();
                model.addAttribute("list", list);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "profile/sign_up";
        }
        userService.signUp(form);
        return "redirect:/signIn";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfile(Authentication authentication, Model model) {
        if (authentication != null){
            User user = userSetup(authentication, model);
            if (user != null){
                BooksDto bookList = bookService.getBooksByAuthor(user.getId());
                model.addAttribute("bookList", bookList.getData());
                return "profile/profile";
            }
        }
        return "redirect:/signIn";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/profile/{id}")
    public String getAuthor(Authentication authentication, Model model, @PathVariable("id")Long id) {
        if (authentication != null){
            User user = userSetup(authentication, model);
            if (user != null && user.getId().equals(id)){
                return "redirect:/profile";
            }
        }
        User author = userService.getUser(id);
        BooksDto bookList = bookService.getBooksByAuthor(author.getId());
        model.addAttribute("bookList", bookList.getData());
        model.addAttribute("author", author);
        return "profile/author";
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

