package ru.linar.sem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.linar.sem.services.BookService;

@Controller
public class TestController {
    @Autowired
    BookService bookService;

    @GetMapping("/test")
    public String get(ModelMap model){
//        model.put("list", bookService.getBooksByAuthor((long) 123));
        return "test2";
    }

//    @GetMapping("/")
//    public String index() {
//        return "redirect:/form";
//    }
//
//    @GetMapping("/form")
//    public String formGet(Model model) {
//        model.addAttribute("user", new SignUpDto());
//        return "test";
//    }
//
//    @PostMapping("/form")
//    public String formPost(@Valid SignUpDto user, BindingResult bindingResult, Model model) {
//        if (!bindingResult.hasErrors()) {
//            model.addAttribute("noErrors", true);
//        }
//        model.addAttribute("user", user);
//        return "test";
//    }
}
