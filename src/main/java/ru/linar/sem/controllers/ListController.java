package ru.linar.sem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.linar.sem.models.User;
import ru.linar.sem.security.details.UserDetailsImpl;
import ru.linar.sem.services.BookService;
import ru.linar.sem.services.UserService;

@Controller
public class ListController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(){
        return "redirect:/list/user/0";
    }

    @GetMapping("/list/user/{page}")
    public String getUserList(Model map, @PathVariable("page") Integer page,Authentication authentication){
        if(authentication != null){
            userSetup(authentication, map);
        }
        map.addAttribute("title", "Authors");
        map.addAttribute("list", userService.getUsers(page));
        System.out.println(userService.getCount());
        map.addAttribute("nextPage",userService.getCount() - 20*(page+1)>0 );
        map.addAttribute("page", page);
        return "listUsers";
    }

    @GetMapping("/list/books/{page}")
    public String getBookList(Model map, @PathVariable("page") Integer page,Authentication authentication){
        if(authentication != null){
            userSetup(authentication, map);
        }
        map.addAttribute("title", "Books");
        map.addAttribute("list", bookService.getBooks(page));
        map.addAttribute("nextPage",bookService.getCount() - 20*(page+1)>0 );
        map.addAttribute("page", page);
        return "listBooks";
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
