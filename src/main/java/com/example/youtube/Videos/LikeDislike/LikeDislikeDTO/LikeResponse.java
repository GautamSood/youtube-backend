package com.example.youtube.Videos.LikeDislike.LikeDislikeDTO;

import com.example.youtube.Videos.LikeDislike.LikeDislike;
import com.example.youtube.Videos.Videos;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeResponse {

    private LikeDislike likeDislike;
    private Videos videos;
}
