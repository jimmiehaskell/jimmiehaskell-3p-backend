package dev.jimmiehaskell.ppp_backend.authentication.controllers.impl;

import dev.jimmiehaskell.ppp_backend.authentication.controllers.LoginController;
import dev.jimmiehaskell.ppp_backend.authentication.services.LoginService;
import dev.jimmiehaskell.ppp_backend.authentication.dtos.LoginRequestDTO;
import dev.jimmiehaskell.ppp_backend.authentication.dtos.LoginResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class LoginControllerImpl implements LoginController {
    private final LoginService service;

    public LoginControllerImpl(LoginService service) { this.service = service; }

    @Override
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return service.login(dto);
    }
}
