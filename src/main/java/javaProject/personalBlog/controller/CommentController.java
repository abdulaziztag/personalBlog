package javaProject.personalBlog.controller;

import javaProject.personalBlog.domain.Comment;
import javaProject.personalBlog.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("comments")
public class CommentController {
    private final CommentRepo commentRepo;

    @Autowired
    public CommentController(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @GetMapping("{postId}")
    public Stream<Comment> list(@PathVariable String postId) {
        List<Comment> comments;
        comments = commentRepo.findAll();
        return comments.stream()
                        .filter(key -> Objects.equals(String.valueOf(key.getPostId()), postId));
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment) {
        comment.setCreationDate(LocalDateTime.now());
        return commentRepo.save(comment);
    }
}
