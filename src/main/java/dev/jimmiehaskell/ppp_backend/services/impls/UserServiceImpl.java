package dev.jimmiehaskell.ppp_backend.services.impls;

import dev.jimmiehaskell.ppp_backend.dtos.UserResponseDTO;
import dev.jimmiehaskell.ppp_backend.repositories.UserRepository;
import dev.jimmiehaskell.ppp_backend.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository user) {
        this.userRepository = user;
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
            .stream()
            .map(UserResponseDTO::new)
            .toList();
    }
}
