package ru.linar.sem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.linar.sem.dto.CommentsDto;
import ru.linar.sem.models.Comment;
import ru.linar.sem.services.CommentService;

import java.util.List;

@RestController
public class RESTController {
    @Autowired
    CommentService commentService;

    @GetMapping("/comment/{chapter-id}/{page}")
    public CommentsDto getComment(@PathVariable("chapter-id") Long id, @PathVariable("page") Integer page){
        List<Comment> list= commentService.getCommentByBookChapter(id, page, 20);
        Boolean nextComment = commentService.getCountOfComments(id) - (page+1) * 20 > 0;
        return CommentsDto.builder().comments(list).nextComment(nextComment).build();
    }
}
