package com.example.youtube.User;
import com.example.youtube.Videos.LikeDislike.LikeDislike;
import com.example.youtube.Subscriber.subscriber;
import com.example.youtube.User.Roles.Role;
import com.example.youtube.Videos.Videos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    @Column(name = "user" , nullable = false)
    private String user;

    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    @Email(message = "write a correct Email")
    @Column(name = "email", nullable = false , unique = true , updatable = false)
    private String email;

    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    @Column(name = "user_pass", nullable = false )
    private String user_pass;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "subscriberid",orphanRemoval = true)
    @JsonIgnore
    List<subscriber> subscribers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "video_user_id" ,  orphanRemoval = true)
    @JsonManagedReference
    List<Videos>  videos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "useridlikeDislike" , orphanRemoval = true)
    @JsonManagedReference
    List<LikeDislike> likeDislike = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return user_pass;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

