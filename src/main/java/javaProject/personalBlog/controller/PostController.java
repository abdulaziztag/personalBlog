package javaProject.personalBlog.controller;

import javaProject.personalBlog.domain.Post;
import javaProject.personalBlog.repo.PostRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("list")
public class PostController {
    private final PostRepo postRepo;

    @Autowired
    public PostController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping
    public List<Post> list() {
        return postRepo.findAll();
    }

    @GetMapping("{id}")
    public Post getOne(@PathVariable("id") Post post) {
        return post;
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        post.setCreationDate(LocalDateTime.now());
        return postRepo.save(post);
    }

    @PutMapping("{id}")
    public Post update(
            @PathVariable("id") Post postFromDB,
            @RequestBody Post post
    ) {
        BeanUtils.copyProperties(post, postFromDB, "id", "creationDate");
        return postRepo.save(postFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Post post) {
        postRepo.delete(post);
    }
}

