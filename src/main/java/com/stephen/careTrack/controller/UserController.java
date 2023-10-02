package com.stephen.careTrack.controller;

import com.stephen.careTrack.DTO.UserDTO;
import com.stephen.careTrack.helper.UserMapper;
import com.stephen.careTrack.model.User;
import com.stephen.careTrack.repository.UserRepository;
import com.stephen.careTrack.security.jwt.JwtProvider;
import com.stephen.careTrack.service.LocationService;
import com.stephen.careTrack.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * The `UserController` class is a Spring REST controller responsible for user management operations.
 * It provides an endpoint to create new user accounts, enforce access control, and use Spring Security for
 * authentication and authorization.
 *
 * Endpoints:
 * - POST /api/v1/user: Create a new user account with administrative privileges.
 *
 * To use this controller, ensure that the necessary Spring Security configuration, user service, and location service
 * are in place. This class is designed to be accessible only to users with an 'ADMIN' role due to the `@PreAuthorize` annotation.
 *
 * @author Ogolla
 * @version 1.0
 * @see com.stephen.careTrack.DTO.UserDTO
 * @see com.stephen.careTrack.model.User
 * @see com.stephen.careTrack.security.jwt.JwtProvider
 * @see com.stephen.careTrack.service.LocationService
 * @see com.stephen.careTrack.service.UserService
 */
@RestController
@RequestMapping("/api/v1")
@Validated
@Tag(name = "User", description = "Manages User operations")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationService locationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider tokenProvider;

    // Create a new user
    @Operation(summary = "Create a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = User.class), mediaType = "application/json"),
            }, description = "Creation OK"),
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/user")
    ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userService.findByUsername(currentUsername);
        User user = UserMapper.dtoToEntity(userDTO);
        user.setRegisteredBy(currentUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createPatient = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createPatient.getId()).toUri();
        return ResponseEntity.created(location).body("Success: User Created");
    }
}
