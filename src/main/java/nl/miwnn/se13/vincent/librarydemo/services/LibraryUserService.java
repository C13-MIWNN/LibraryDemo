package nl.miwnn.se13.vincent.librarydemo.services;

import nl.miwnn.se13.vincent.librarydemo.dtos.LibraryUserFormDTO;
import nl.miwnn.se13.vincent.librarydemo.model.LibraryUser;
import nl.miwnn.se13.vincent.librarydemo.repositories.LibraryUserRepository;
import nl.miwnn.se13.vincent.librarydemo.services.mappers.LibraryUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryUserService implements UserDetailsService {
    private final LibraryUserRepository libraryUserRepository;
    private final PasswordEncoder passwordEncoder;

    public LibraryUserService(LibraryUserRepository libraryUserRepository, PasswordEncoder passwordEncoder) {
        this.libraryUserRepository = libraryUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return libraryUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public boolean userExists(String username) {
        return libraryUserRepository.findByUsername(username).isPresent();
    }

    public void saveUser(LibraryUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        libraryUserRepository.save(user);
    }

    public void saveUser(LibraryUserFormDTO dto) {
        saveUser(LibraryUserMapper.fromDTO(dto));
    }

    public boolean isNotInitialised() {
        return libraryUserRepository.count() == 0;
    }
}
