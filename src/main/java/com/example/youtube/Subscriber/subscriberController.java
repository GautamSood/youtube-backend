package com.example.youtube.Subscriber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sub")
public class subscriberController {

    @GetMapping("/hi")
    public String hi(){
        return "jj";
    }
}
