package javaProject.personalBlog.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("adminLogin")
public class LoginController {
    private final String username = "admin";
    private final String password = "123456";

    @PostMapping
    public Boolean login(@RequestBody Map<String, String> user) {
        return Objects.equals(user.get("username"), username) && Objects.equals(user.get("password"), password);
    }
}
