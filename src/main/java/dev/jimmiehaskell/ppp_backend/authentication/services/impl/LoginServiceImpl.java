package dev.jimmiehaskell.ppp_backend.authentication.services.impl;

import dev.jimmiehaskell.ppp_backend.authentication.services.LoginService;
import dev.jimmiehaskell.ppp_backend.authentication.dtos.LoginRequestDTO;
import dev.jimmiehaskell.ppp_backend.authentication.dtos.LoginResponseDTO;
import dev.jimmiehaskell.ppp_backend.entities.Role;
import dev.jimmiehaskell.ppp_backend.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.util.stream.Collectors;

public class LoginServiceImpl implements LoginService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    public LoginServiceImpl(BCryptPasswordEncoder passwordEncoder,
                            JwtEncoder jwtEncoder,
                            UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO request) {
        var user = userRepository.findByUsername(request.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(request, passwordEncoder)) {
            throw new BadCredentialsException("Usuário ou senha inválido.");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var scopes = user.get().getRoles()
            .stream()
            .map(Role::getName)
            .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
            .issuer("3P")
            .subject(user.get().getUserId().toString())
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiresIn))
            .claim("scope", scopes)
            .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiresIn));
    }
}
