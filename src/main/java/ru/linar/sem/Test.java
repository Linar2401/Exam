package ru.linar.sem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.linar.sem.services.CountryListService;

import java.io.IOException;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("linar@mail.ru"));
    }
}
