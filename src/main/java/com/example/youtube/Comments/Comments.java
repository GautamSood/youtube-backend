//package com.example.youtube.Comments;
//
//import com.example.youtube.User.User;
//import com.example.youtube.Videos.videos;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//public class Comments {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User userid;
//
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "video_id", nullable = false)
//    private videos videoid;
//
//    @NotNull
//    @Column(length = 100000, nullable = false)
//    private String comment;
//
//}
