package com.example.youtube.Subscriber.SubscriberController;
import com.example.youtube.Subscriber.SubscriberDTO.SubscriberResponse;
import com.example.youtube.Subscriber.SubscriberService.SubscriberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class subscriberController {

    private final SubscriberService subscriberService;

    @PostMapping("/sub/{userId}/{subsId}")
    public ResponseEntity<SubscriberResponse> subs(
            @Valid
            @PathVariable(value = "userId") long userId,
            @PathVariable(value = "subsId") long subsId
    ){
        return ResponseEntity.ok(subscriberService.subs(userId,subsId));
    }
    @PostMapping("/unsub/{userId}/{subsId}")
    public ResponseEntity<String> unsub(
            @Valid
            @PathVariable(value = "userId") long userId,
            @PathVariable(value = "subsId") long subsId
    ){
        return ResponseEntity.ok(subscriberService.unsubs(userId,subsId));
    }
}
