package com.example.youtube.Videos.LikeDislike;

import com.example.youtube.User.User;
import com.example.youtube.Videos.Videos;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeDislikeRepository extends JpaRepository<LikeDislike, Long> {
    @Override
    Optional<LikeDislike> findById(Long aLong);

    Optional<LikeDislike> findByUseridlikeDislikeAndVideolikeDislike(@NotNull(message = "null") User useridlikeDislike, @NotNull(message = "null") Videos videolikeDislike);
}