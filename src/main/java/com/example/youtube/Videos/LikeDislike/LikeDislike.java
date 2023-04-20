package com.example.youtube.Videos.LikeDislike;

import com.example.youtube.User.User;
import com.example.youtube.Videos.Videos;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDislike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "null")
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "userid_likeDislike", nullable = false)
    private User useridlikeDislike;

    @NotNull(message = "null")
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "video_likeDislike", nullable = false)
    private Videos videolikeDislike;

    @Column()
    private boolean isLike;

}
