package ru.linar.sem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book not exist")
public class BookNotExistException extends IllegalArgumentException {
    public BookNotExistException() {
    }

    public BookNotExistException(String s) {
        super(s);
    }

    public BookNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookNotExistException(Throwable cause) {
        super(cause);
    }
}
