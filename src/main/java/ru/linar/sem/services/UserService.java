package ru.linar.sem.services;

import ru.linar.sem.dto.forms.SignUpForm;
import ru.linar.sem.models.User;

import java.util.List;

public interface UserService {
    public boolean signUp(SignUpForm form);
    public User getUser(Long id);
    public Integer getCount();
    public List<User> getUsers(Integer page);
}
