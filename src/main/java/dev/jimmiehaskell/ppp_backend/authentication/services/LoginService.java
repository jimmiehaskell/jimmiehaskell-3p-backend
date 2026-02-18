package dev.jimmiehaskell.ppp_backend.authentication.services;

import dev.jimmiehaskell.ppp_backend.authentication.dtos.LoginRequestDTO;
import dev.jimmiehaskell.ppp_backend.authentication.dtos.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<LoginResponseDTO> login(LoginRequestDTO dto);
}
