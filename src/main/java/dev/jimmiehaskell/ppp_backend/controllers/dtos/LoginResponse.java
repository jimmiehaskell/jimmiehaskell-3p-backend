package dev.jimmiehaskell.ppp_backend.controllers.dtos;

public record LoginResponse(String accessToken, long expiresIn) {
}
