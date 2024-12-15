package vn.techzen.academy_pnv_25.controllor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
@CrossOrigin(origins = "http://localhost:5173")
public class HelloController {
    @GetMapping
    public String hello(@RequestParam(defaultValue = " ") String name) {
        return String.format("hello %s!!!", name);
    }
}
