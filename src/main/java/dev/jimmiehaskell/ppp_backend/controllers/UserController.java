package dev.jimmiehaskell.ppp_backend.controllers;

import dev.jimmiehaskell.ppp_backend.dtos.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public interface UserController {
    @GetMapping()
    ResponseEntity<List<UserResponseDTO>> listUsers();
}
