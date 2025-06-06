package com.example.User_Note.service;

import com.example.User_Note.exception.EmptyFieldException;
import com.example.User_Note.exception.InvalidCredentialsException;
import com.example.User_Note.exception.UsernameExistsException;
import com.example.User_Note.model.Users;
import com.example.User_Note.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void validateUser(String username, String password) throws InvalidCredentialsException
    {
        boolean valid = userRepository.findByUsername(username).map(user -> bCryptPasswordEncoder.matches(password,user.getPassword())).orElse(false);
        if(!valid)
        {
            throw new InvalidCredentialsException();
        }
    }

    public void addUser(Users user) throws UsernameExistsException, EmptyFieldException
    {
        if(user.getUsername().isEmpty())
        {
            throw new EmptyFieldException("username");
        }
        else if(user.getPassword().isEmpty())
        {
            throw new EmptyFieldException("password");
        }
        else if(user.getRole().name().isEmpty())
        {
            throw new EmptyFieldException("role");
        }
        if(userRepository.findByUsername(user.getUsername()).isPresent())
        {
            throw new UsernameExistsException();
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Users user = userRepository.findByUsername(username).orElse(null);
        return new User(
                    Objects.requireNonNull(user).getUsername(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}
