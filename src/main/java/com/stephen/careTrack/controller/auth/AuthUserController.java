package com.stephen.careTrack.controller.auth;

import com.stephen.careTrack.DTO.LoginDTO;
import com.stephen.careTrack.DTO.PatientDTO;
import com.stephen.careTrack.DTO.UserDTO;
import com.stephen.careTrack.helper.PatientMapper;
import com.stephen.careTrack.helper.UserMapper;
import com.stephen.careTrack.model.Location;
import com.stephen.careTrack.model.Patient;
import com.stephen.careTrack.model.User;
import com.stephen.careTrack.repository.UserRepository;
import com.stephen.careTrack.security.jwt.JwtProvider;
import com.stephen.careTrack.security.jwt.JwtResponse;
//import com.stephen.careTrack.service.UserService;
import com.stephen.careTrack.security.jwt.JwtResponseWithUserDetails;
import com.stephen.careTrack.service.JwtTokenBlacklist;
import com.stephen.careTrack.service.LocationService;
import com.stephen.careTrack.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * The `UserController` class is a Spring REST controller responsible for user authentication and sign-out operations.
 * It provides endpoints for user sign-in and sign-out functionalities.
 *
 * This controller integrates with Spring Security and JWT (JSON Web Token) authentication to authenticate users
 * and generate JWT tokens. It also includes a basic token blacklist mechanism to support user sign-out.
 *
 * Endpoints:
 * - POST /auth/signin: Authenticate a user and generate a JWT token for successful logins.
 * - POST /auth/signout: Blacklist a JWT token, effectively signing out the user. Requires a valid JWT token in the request header.
 *
 * To use this controller, make sure that the necessary Spring Security configuration, user service, and token blacklist service are set up.
 * The `JwtTokenBlacklist` service is used to maintain a blacklist of invalidated tokens.
 *
 * @author Ogolla
 * @version 1.0
 * @see com.stephen.careTrack.DTO.LoginDTO
 * @see com.stephen.careTrack.model.User
 * @see com.stephen.careTrack.security.jwt.JwtProvider
 * @see com.stephen.careTrack.service.JwtTokenBlacklist
 * @see com.stephen.careTrack.service.UserService
 */

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth user", description = "Manages User authentication")
public class AuthUserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationService locationService;

    @Autowired
    private JwtTokenBlacklist jwtTokenBlacklist;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider tokenProvider;
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginDTO login) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        User authenticatedUser = userService.findByUsername(login.getUsername());
        Location userLocation = locationService.getLocationByUser(authenticatedUser);
        JwtResponseWithUserDetails response = new JwtResponseWithUserDetails(jwt, authenticatedUser, userLocation);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signout")
    public ResponseEntity<String> signoutUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7).trim();
            jwtTokenBlacklist.addToBlacklist(token);

            return ResponseEntity.ok("User successfully signed out.");
        }

        return ResponseEntity.badRequest().body("Invalid token format.");
    }
}
