package ru.linar.sem.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.linar.sem.models.User;
import ru.linar.sem.security.details.UserDetailsImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorTemplateController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Authentication authentication, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (authentication != null && authentication.isAuthenticated()){
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();
            model.addAttribute("user", user);
        }
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errors/404";
            }
        }
        return "errors/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}