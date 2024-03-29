package org.MadManager.medmanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.MadManager.medmanager.dao.RoleRepository;
import org.MadManager.medmanager.dao.UserRepository;
import org.MadManager.medmanager.models.Role;
import org.MadManager.medmanager.models.RoleName;
import org.MadManager.medmanager.models.User;
import org.MadManager.medmanager.payload.APIResponse;
import org.MadManager.medmanager.payload.JwtAuthenticationResponse;
import org.MadManager.medmanager.payload.LoginRequest;
import org.MadManager.medmanager.payload.SingnupRequest;
import org.MadManager.medmanager.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authrnticateUser(@Valid @RequestBody LoginRequest loginRequest){
        LOGGER.info("Login request recieved for user: {}",loginRequest.getUsernameOrEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        LOGGER.info("User {} logged in",loginRequest.getUsernameOrEmail());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingnupRequest singnupRequest) throws Exception {

        User user = new User(singnupRequest.getName(),singnupRequest.getUsername(),singnupRequest.getPassword(),singnupRequest.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRoles = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(()->new Exception("User role not set"));
        user.setRoles(Collections.singleton(userRoles));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/users/{username}")
                    .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new APIResponse(true,"User Created Successfully"));
    }
}
