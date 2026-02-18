package dev.jimmiehaskell.ppp_backend.authentication.controllers.impl;

import dev.jimmiehaskell.ppp_backend.authentication.controllers.CadastroController;
import dev.jimmiehaskell.ppp_backend.authentication.dtos.CreateUserRequestDTO;
import dev.jimmiehaskell.ppp_backend.authentication.services.CadastroService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CadastroControllerImpl implements CadastroController {
    private final CadastroService service;

    public CadastroControllerImpl(CadastroService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Void> cadastro(@RequestBody CreateUserRequestDTO dto) {
        return service.cadastro(dto);
    }
}
