package com.example.youtube.Videos;

import com.example.youtube.Videos.Comments.Comments;
import com.example.youtube.Videos.LikeDislike.LikeDislike;
import com.example.youtube.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Videos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    @Column(name = "video_url", nullable = false)
    private String video_url;

    @Column(name = "is_baned")
    private boolean Baned;

    @Column(name = "age_restrict")
    private boolean ageRestrict;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_user_id", nullable = false )
    @JsonBackReference
    private User video_user_id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "videolikeDislike")
    @JsonManagedReference
    List<LikeDislike> video_likeDislikes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "videoid")
    @JsonManagedReference
    List<Comments> videosComments = new ArrayList<>();
}
