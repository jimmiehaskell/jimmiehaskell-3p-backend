package dev.jimmiehaskell.ppp_backend.authentication.controllers;

import dev.jimmiehaskell.ppp_backend.authentication.dtos.LoginRequestDTO;
import dev.jimmiehaskell.ppp_backend.authentication.dtos.LoginResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public interface LoginController {
    @PostMapping()
    ResponseEntity<LoginResponseDTO> login(LoginRequestDTO dto);
}
