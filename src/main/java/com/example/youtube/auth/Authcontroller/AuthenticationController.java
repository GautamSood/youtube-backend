package com.example.youtube.auth.Authcontroller;

import com.example.youtube.auth.AuthService.AuthenticationService;
import com.example.youtube.auth.DTO.AuthenticationRequest;
import com.example.youtube.auth.DTO.AuthenticationResponse;
import com.example.youtube.auth.DTO.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody @Valid RegisterRequest request
    ){
        try{
            return ResponseEntity.ok(authenticationService.register(request));
        }catch (DataIntegrityViolationException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("This user is already in dataBase");
        }catch (TransactionSystemException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Invalid request body");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.toString());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ){
        try{
            return ResponseEntity.ok(authenticationService.authenticate(request));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthenticationResponse("Invalid credentials.",null));
        }
    }
}
