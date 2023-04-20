package com.example.youtube.Videos.LikeDislike.LikeConlroller;

import com.example.youtube.Videos.LikeDislike.LikeDislikeDTO.LikeResponse;
import com.example.youtube.Videos.LikeDislike.LikeDislikeService.LikeDislikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vids/likedislike")
@RequiredArgsConstructor
public class LikeController {
    private final LikeDislikeService likeDislikeService;
    @PostMapping("/{userId}/{vidId}")
    public ResponseEntity<LikeResponse> Like(
            @Valid
            @PathVariable(value = "userId") long userId,
            @PathVariable(value = "vidId") long vidId
    ){
        return ResponseEntity.ok(likeDislikeService.likeVid(userId,vidId));
    }
    @PostMapping("/dislike/{userId}/{vidId}")
    public ResponseEntity<LikeResponse> DisLike(
            @Valid
            @PathVariable(value = "userId") long userId,
            @PathVariable(value = "vidId") long vidId
    ){
        return ResponseEntity.ok(likeDislikeService.dislikeVid(userId,vidId));
    }
}
