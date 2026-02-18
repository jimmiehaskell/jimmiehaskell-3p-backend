package dev.jimmiehaskell.ppp_backend.authentication.services;

import dev.jimmiehaskell.ppp_backend.authentication.dtos.CreateUserRequestDTO;
import org.springframework.http.ResponseEntity;

public interface CadastroService {
    ResponseEntity<Void> cadastro(CreateUserRequestDTO request);
}
