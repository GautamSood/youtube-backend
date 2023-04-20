package com.example.youtube.Videos.LikeDislike.LikeDislikeService;

import com.example.youtube.User.UserRepository;
import com.example.youtube.Videos.LikeDislike.LikeDislike;
import com.example.youtube.Videos.LikeDislike.LikeDislikeDTO.LikeResponse;
import com.example.youtube.Videos.LikeDislike.LikeDislikeRepository;
import com.example.youtube.Videos.VideosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeDislikeService {

    private final VideosRepository videosRepository;
    private final UserRepository userRepository;
    private final LikeDislikeRepository likeDislikeRepository;

    @Transactional
    public LikeResponse likeVid(long userId, long vidId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        var video = videosRepository.findById(vidId).orElseThrow(() -> new UsernameNotFoundException("video not found"));

        if(likeDislikeRepository.findByUseridlikeDislikeAndVideolikeDislike(user, video).isPresent()){
            var like = likeDislikeRepository.findByUseridlikeDislikeAndVideolikeDislike(user,video).orElseThrow(() -> new UsernameNotFoundException("video not found"));
            like.setLike(true);
            likeDislikeRepository.save(like);
            LikeResponse lt = LikeResponse.builder().likeDislike(like).build();
            return lt;
        }

        var like = LikeDislike.builder()
                .videolikeDislike(video)
                .useridlikeDislike(user)
                .isLike(true)
                .build();

        List<LikeDislike> likeDislikeList1 = user.getLikeDislike();
        likeDislikeList1.add(like);
        user.setLikeDislike(likeDislikeList1);

        userRepository.save(user);
        LikeResponse lt = LikeResponse.builder().likeDislike(like).videos(video).build();

        return lt;
    }

    @Transactional
    public LikeResponse dislikeVid(long userId, long vidId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        var video = videosRepository.findById(vidId).orElseThrow(() -> new UsernameNotFoundException("video not found"));

        if(likeDislikeRepository.findByUseridlikeDislikeAndVideolikeDislike(user, video).isPresent()){
            var like = likeDislikeRepository.findByUseridlikeDislikeAndVideolikeDislike(user,video).orElseThrow(() -> new UsernameNotFoundException("video not found"));
            like.setLike(false);
            likeDislikeRepository.save(like);
            LikeResponse lt = LikeResponse.builder().likeDislike(like).videos(video).build();
            return lt;
        }
        var like = LikeDislike.builder()
                .videolikeDislike(video)
                .useridlikeDislike(user)
                .isLike(false)
                .build();

        List<LikeDislike> likeDislikeList1 = user.getLikeDislike();
        likeDislikeList1.add(like);
        user.setLikeDislike(likeDislikeList1);
        userRepository.save(user);
        LikeResponse lt = LikeResponse.builder().likeDislike(like).videos(video).build();

        return lt;
    }


}
