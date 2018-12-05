package com.jarvisk.exam.ouath2.oauth2exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<AppUser> users() {
        return userService.findAll();
    }

    @PostMapping()
    public AppUser create(@RequestBody AppUser user) {
        return userService.save(user);
    }

    @GetMapping("/${username}")
    public ResponseEntity<Void> user(@PathVariable String username) {
        userService.delete(username);
        return ResponseEntity.ok().build();
    }
}
