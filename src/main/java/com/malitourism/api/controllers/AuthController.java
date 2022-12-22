package com.malitourism.api.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import com.malitourism.api.security.services.CrudService;
import com.malitourism.api.security.jwt.JwtUtils;
import com.malitourism.api.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malitourism.api.models.ERole;
import com.malitourism.api.models.Role;
import com.malitourism.api.models.User;
import com.malitourism.api.payload.request.LoginRequest;
import com.malitourism.api.payload.request.SignupRequest;
import com.malitourism.api.payload.response.JwtResponse;
import com.malitourism.api.payload.response.MessageResponse;
import com.malitourism.api.repository.RoleRepository;
import com.malitourism.api.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  private CrudService crudService;
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
    log.info("Le collaborateur "+userDetails.getUsername() + " vient de" +" se connecter");

    return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles
    ));

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      log.error("Erreur: Cet nom d'utilisateur existe déjà!");
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Erreur: Cet nom d'utilisateur existe déjà!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      log.error("Erreur: Cet email existe déjà!");
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Erreur: Cet email existe déjà!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));
            log.info("Utilisateur crée "+user);
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Erreur: Role non trouver."));
      log.error("role non trouvé"+userRole);
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            log.error("Erreur: Role non trouver.");
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Erreur: Role non trouver."));
            roles.add(adminRole);
            break;
          default:
            log.error("Erreur: Role non trouver.");
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Erreur: Role non trouver."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);
    log.info("Utilisateur crée "+user.getUsername());

    return ResponseEntity.ok(new MessageResponse("Utilisateur crée avec succès!"));

  }




  @RolesAllowed({"USER","ADMIN"})
  @RequestMapping("/admin")
  public List<User> AfficherUsers()
  {
    return crudService.Afficher();
  }




  /*@RequestMapping("/*")
  public String getGithub(Principal user)
  {

    return "Bienvenu " ;
  }*/


}
