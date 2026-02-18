package dev.jimmiehaskell.ppp_backend.controllers.impl;

import dev.jimmiehaskell.ppp_backend.controllers.UserController;
import dev.jimmiehaskell.ppp_backend.dtos.UserResponseDTO;
import dev.jimmiehaskell.ppp_backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserControllerImpl implements UserController {
    private final UserService service;

    public UserControllerImpl(UserService service) { this.service = service; }

    @Override
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
}
