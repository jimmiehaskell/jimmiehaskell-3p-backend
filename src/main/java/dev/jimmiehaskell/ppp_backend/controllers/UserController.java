package dev.jimmiehaskell.ppp_backend.controllers;

import dev.jimmiehaskell.ppp_backend.controllers.dtos.CreateUserDTO;
import dev.jimmiehaskell.ppp_backend.entities.Role;
import dev.jimmiehaskell.ppp_backend.entities.User;
import dev.jimmiehaskell.ppp_backend.repositories.RoleRepository;
import dev.jimmiehaskell.ppp_backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @PostMapping("/users")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDTO dto) {
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        var userFromDB = userRepository.findByUsername(dto.username());

        if (userFromDB.isPresent()) {
            throw  new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUsers() {
        var listUsers = userRepository.findAll();
        return ResponseEntity.ok(listUsers);
    }
}
