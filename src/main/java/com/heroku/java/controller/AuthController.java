package com.heroku.java.controller;

import com.heroku.java.model.EncryptionUtil;
import com.heroku.java.model.UserRoles;
import com.heroku.java.model.authentication.JwtUtil;
import com.heroku.java.model.User;
import com.heroku.java.model.request.LoginReq;
import com.heroku.java.model.response.ErrorRes;
import com.heroku.java.model.response.LoginRes;
import com.heroku.java.repository.UserRepository;
import com.heroku.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    // Verify token from a request
    @GetMapping(value = "/verify")
    public ResponseEntity verify(@RequestParam String token) {
        try {
            String email = jwtUtil.getEmailFromToken(token);
            User user = new User(email, "", new UserRoles());
            String newToken = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(email, newToken);
            return ResponseEntity.ok(loginRes);
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid token");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq) {
        try {
            System.out.println(loginReq.getEmail() + "´, " + loginReq.getPassword());

            // auther
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();

            // det vi returner til kient
            User user = new User(email, "", new UserRoles());
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(email, token);

            return ResponseEntity.ok(loginRes);
        } catch (BadCredentialsException e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            System.out.println("her");
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}