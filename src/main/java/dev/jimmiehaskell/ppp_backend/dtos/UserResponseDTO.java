package dev.jimmiehaskell.ppp_backend.dtos;

import dev.jimmiehaskell.ppp_backend.entities.User;

public record UserResponseDTO(String username) {
    public UserResponseDTO(User user) {
        this(
            user.getUsername()
        );
    }
}
