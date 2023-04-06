package com.example.youtube.User;
import com.example.youtube.LikeDislike.likeDislike;
import com.example.youtube.Subscriber.subscriber;
import com.example.youtube.User.Roles.Role;
import com.example.youtube.Videos.videos;
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

    @NotNull
    @Column(name = "user" , nullable = false)
    private String user;

    @NotNull
    @Email(message = "write a correct Email")
    @Column(name = "email", nullable = false , unique = true , updatable = false)
    @NotEmpty
    @NotBlank
    private String email;

    @NotNull
    @Column(name = "user_pass", nullable = false )
    @NotEmpty
    @NotBlank
    private String user_pass;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "subscriber_id",orphanRemoval = true)
    List<subscriber> subscribers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "video_user_id" ,  orphanRemoval = true)
    List<videos>  videos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "userid_likeDislike" , orphanRemoval = true)
    List<likeDislike> likeDislike = new ArrayList<>();

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

