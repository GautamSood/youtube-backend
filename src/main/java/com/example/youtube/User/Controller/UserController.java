package com.example.youtube.User.Controller;

import com.example.youtube.Subscriber.subscriber;
import com.example.youtube.Subscriber.subscriberRepository;
import com.example.youtube.User.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.youtube.User.User;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private com.example.youtube.User.userRepository userRepository;

    private com.example.youtube.Subscriber.subscriberRepository subscriberRepository;

    @GetMapping("/getuser")
    public ResponseEntity<String> test(){
        System.out.println("yo");
    return ResponseEntity.ok("nice testing");
    }
}
