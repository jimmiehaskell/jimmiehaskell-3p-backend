package dev.jimmiehaskell.ppp_backend.authentication.services.impl;

import dev.jimmiehaskell.ppp_backend.authentication.dtos.CreateUserRequestDTO;
import dev.jimmiehaskell.ppp_backend.authentication.services.CadastroService;
import dev.jimmiehaskell.ppp_backend.entities.Role;
import dev.jimmiehaskell.ppp_backend.entities.User;
import dev.jimmiehaskell.ppp_backend.repositories.RoleRepository;
import dev.jimmiehaskell.ppp_backend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class CadastroServiceImpl implements CadastroService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public CadastroServiceImpl(BCryptPasswordEncoder passwordEncoder,
                               RoleRepository roleRepository,
                               UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Void> cadastro(CreateUserRequestDTO dto) {
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        var userFromDB = userRepository.findByUsername(dto.username());

        if (userFromDB.isPresent()) {
            throw new ResponseStatusException(HttpStatus.LOCKED);
        }

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
