package com.heroku.java.controller;

import com.heroku.java.model.UserRolesLookup;
import com.heroku.java.model.authentication.JwtUtil;
import com.heroku.java.model.User;
import com.heroku.java.model.request.LoginReq;
import com.heroku.java.model.response.ErrorRes;
import com.heroku.java.model.response.LoginRes;
import com.heroku.java.service.UserService;

import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    // Verify token from a request
    @GetMapping(value = "/verify")
    public ResponseEntity verify(@RequestParam String token) {
        try {

            Claims claims = jwtUtil.parseJwtClaims(token);
            if (jwtUtil.validateClaims(claims)) {
                String username = jwtUtil.getEmailFromToken(token);
                User user = userService.findUserByUsername(username);
                System.out.println("Verifying: "+user.getUsername());

                List<UserRolesLookup> roles = userService.getRolesByUserId(user.getUserId());
                String newToken = jwtUtil.createToken(user);

                LoginRes loginRes = new LoginRes(username, newToken, user, roles);
                return ResponseEntity.ok(loginRes);
            } else {
                throw new Exception("Invalid token");
            }
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid token");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq) {
        try {
            System.out.println("Logging in: "+loginReq.getEmail());

            // auther
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String username = authentication.getName();

            // det vi returner til kient
            User user = userService.findUserByUsername(username);
            user.setPassword(""); //Making sure to send unusable password as response. // Det et hack basiclly

            List<UserRolesLookup> roles = userService.getRolesByUserId(user.getUserId());
            String token = jwtUtil.createToken(user);

            LoginRes loginRes = new LoginRes(username, token, user, roles);

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