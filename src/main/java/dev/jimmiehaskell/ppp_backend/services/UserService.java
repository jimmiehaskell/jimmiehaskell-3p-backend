package dev.jimmiehaskell.ppp_backend.services;

import dev.jimmiehaskell.ppp_backend.dtos.UserResponseDTO;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    List<UserResponseDTO> findAll();
}
