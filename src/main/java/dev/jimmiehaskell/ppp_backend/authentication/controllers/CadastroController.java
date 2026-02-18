package dev.jimmiehaskell.ppp_backend.authentication.controllers;

import dev.jimmiehaskell.ppp_backend.authentication.dtos.CreateUserRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public interface CadastroController {
    @Transactional
    @PostMapping()
    ResponseEntity<Void> cadastro(@RequestBody CreateUserRequestDTO dto);
}
