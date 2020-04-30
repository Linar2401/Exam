package ru.linar.sem.services;

import ru.linar.sem.dto.forms.CommentForm;
import ru.linar.sem.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByBookChapter(Long id,Integer page, Integer size);
    void addComment(CommentForm form);
    Integer getCountOfComments(Long id);
}
