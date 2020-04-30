package ru.linar.sem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book chapter not found")
public class ChapterNotFoundException  extends IllegalArgumentException {
    public ChapterNotFoundException() {
    }

    public ChapterNotFoundException(String s) {
        super(s);
    }

    public ChapterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChapterNotFoundException(Throwable cause) {
        super(cause);
    }
}
