package vn.techzen.academy_pnv_25;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String hello(@RequestParam(defaultValue = "Guest") String name) {
        return String.format("Welcome to Spring Boot 3, learning at StudyArt %s!!!", name);
    }
}
