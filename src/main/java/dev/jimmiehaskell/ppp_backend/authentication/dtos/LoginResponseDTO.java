package dev.jimmiehaskell.ppp_backend.authentication.dtos;

public record LoginResponseDTO(String accessToken, long expiresIn) {
}
