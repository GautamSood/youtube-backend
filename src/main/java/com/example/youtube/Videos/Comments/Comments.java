package com.example.youtube.Videos.Comments;

import com.example.youtube.User.User;
import com.example.youtube.Videos.Videos;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User userid;

    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "video_id", nullable = false)
    private Videos videoid;

    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    @Column(length = 100000, nullable = false)
    private String comment;

}
