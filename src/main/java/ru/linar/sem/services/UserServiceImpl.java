package ru.linar.sem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.linar.sem.dto.forms.SignUpForm;
import ru.linar.sem.exceptions.UserNotFoundException;
import ru.linar.sem.models.Role;
import ru.linar.sem.models.User;
import ru.linar.sem.repositories.UserRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
//
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean signUp(SignUpForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .country(form.getCountry())
                .createdAt(LocalDateTime.now())
                .gender(form.isGender())
                .nickname(form.getNickname())
                .role(Role.USER)
                .passwordHash(passwordEncoder.encode(form.getPassword()))
                .build();
        userRepo.save(user);
        return true;
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()){
            log.error("User with id:" + id + " is not exist");
            throw new UserNotFoundException("User with id:" + id + " is not exist");
        }
        return user.get();
    }

    @Override
    public Integer getCount() {
        return (int) userRepo.count();
    }

    @Override
    public List<User> getUsers(Integer page) {
        Sort sort = Sort.by("nickname");
        PageRequest request = PageRequest.of(page, 20, sort);
        Page<User> pageResult = userRepo.findAll(request);
        return pageResult.getContent();
    }

}
