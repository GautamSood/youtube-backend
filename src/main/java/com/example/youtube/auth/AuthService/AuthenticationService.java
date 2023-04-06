package com.example.youtube.auth.AuthService;

import com.example.youtube.SecurityConfig.JwtService;
import com.example.youtube.User.Roles.Role;
import com.example.youtube.User.User;
import com.example.youtube.auth.DTO.AuthenticationRequest;
import com.example.youtube.auth.DTO.AuthenticationResponse;
import com.example.youtube.auth.DTO.RegisterRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final com.example.youtube.User.userRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public String register(RegisterRequest request) {

            String password = request.getPassword();
            if (password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Password cannot be null or empty");
            }
            var user1 = User.builder()
                    .user(request.getUsername())
                    .email(request.getEmail())
                    .role(myuser(request.getUserType()))
                    .user_pass(passwordEncoder.encode(request.getPassword()))
                    .build();
            userRepository.save(user1);
            var jwtToken = jwtService.generateToken(user1);
            return("user Signed in");

//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user1 = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user1);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user1)
                .build();
    }

    private Role myuser(String str){
        if(str.contains("U") || str.contains("u")){
            return Role.USER;
        }
        return Role.ADMIN;
    }
}
