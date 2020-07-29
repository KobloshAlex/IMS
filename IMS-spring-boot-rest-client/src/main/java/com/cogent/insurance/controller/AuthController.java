package com.cogent.insurance.controller;

import com.cogent.insurance.entity.Role;
import com.cogent.insurance.entity.User;
import com.cogent.insurance.model.payload.request.LoginRequest;
import com.cogent.insurance.model.payload.request.MessageResponse;
import com.cogent.insurance.model.payload.request.SignupRequest;
import com.cogent.insurance.model.payload.responce.JwtResponse;
import com.cogent.insurance.model.response.operations.OperationStatusModel;
import com.cogent.insurance.model.response.operations.RequestOperationName;
import com.cogent.insurance.model.response.operations.RequestOperationStatus;
import com.cogent.insurance.security.jwt.JwtUtils;
import com.cogent.insurance.security.services.UserDetailsImpl;
import com.cogent.insurance.service.UserService;
import com.cogent.insurance.service.impl.AmazonSES;
import com.cogent.insurance.shared.Roles;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.repository.RoleRepository;
import com.cogent.insurance.shared.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  final AuthenticationManager authenticationManager;
  final UserRepository userRepository;
  final RoleRepository roleRepository;
  final BCryptPasswordEncoder bCryptPasswordEncoder;
  final JwtUtils jwtUtils;
  final Utils utils;
  final UserService userService;

  public AuthController(
      AuthenticationManager authenticationManager,
      UserRepository userRepository,
      RoleRepository roleRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      JwtUtils jwtUtils,
      Utils utils,
      UserService userService) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.jwtUtils = jwtUtils;
    this.utils = utils;
    this.userService = userService;
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    return ResponseEntity.ok(
        new JwtResponse(
            jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    final String userId = utils.generateId(20);
    User user =
        new User(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            bCryptPasswordEncoder.encode(signUpRequest.getPassword()),
                userId);

    user.setEmailVerificationToken(utils.generateEmailVerificationToken(userId));
    user.setEmailVerificationStatus(false);

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole =
          roleRepository
              .findByName(Roles.ROLE_CUSTOMER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(
          role -> {
            switch (role) {
              case "ceo":
                Role ceoRole =
                    roleRepository
                        .findByName(Roles.ROLE_CEO)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(ceoRole);

                break;
              case "manager":
                Role managerRole =
                    roleRepository
                        .findByName(Roles.ROLE_MANAGER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(managerRole);

                break;
              case "agent":
                Role agentRole =
                    roleRepository
                        .findByName(Roles.ROLE_AGENT)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(agentRole);

                break;
              default:
                Role userRole =
                    roleRepository
                        .findByName(Roles.ROLE_CUSTOMER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            }
          });
    }

    user.setRoles(roles);
    userRepository.save(user);

    new AmazonSES().verifyEmail(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @GetMapping(path = "/email-verification")
  public OperationStatusModel verifyEmailToken(@RequestParam(value = "token") String token) {

    OperationStatusModel returnValue = new OperationStatusModel();
    returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());

    boolean isVerified = userService.verifyEmailToken(token);

    if (isVerified) {
      returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
    } else {
      returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
    }

    return returnValue;
  }
}
