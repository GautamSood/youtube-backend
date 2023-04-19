package com.example.youtube.LikeDislike;

import com.example.youtube.User.User;
import com.example.youtube.Videos.videos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class likeDislike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userid_likeDislike", nullable = false)
    private User userid_likeDislike;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "video_likeDislike", nullable = false)
    private videos video_likeDislike;

    @Column()
    private boolean isLike;

}
