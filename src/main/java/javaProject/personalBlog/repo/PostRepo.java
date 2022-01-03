package javaProject.personalBlog.repo;

import javaProject.personalBlog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {

}
