package javaProject.personalBlog.repo;

import javaProject.personalBlog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}
