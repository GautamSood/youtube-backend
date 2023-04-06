package com.example.youtube.Videos;

import com.example.youtube.LikeDislike.likeDislike;
import com.example.youtube.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class videos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    @NotEmpty
    @NotBlank
    private String title;

    @NotNull
    @Column(name = "description", nullable = false)
    @NotEmpty
    @NotBlank
    private String description;

    @NotNull
    @Column(name = "video_url", nullable = false)
    @NotEmpty
    @NotBlank
    private String video_url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_user_id", nullable = false )
    private User video_user_id;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "video_likeDislike")
    List<likeDislike> video_likeDislikes = new ArrayList<>();

}
