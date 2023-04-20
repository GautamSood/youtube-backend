package com.example.youtube.User.UserController;

import com.example.youtube.User.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserRepository userRepository;

    private com.example.youtube.Subscriber.subscriberRepository subscriberRepository;

    @GetMapping("/removeUser/{userId}")
    public ResponseEntity<String> test(
            @PathVariable(value = "userId") long userId
    ){
        System.out.println("yo");
    return ResponseEntity.ok("nice testing");
    }
}
