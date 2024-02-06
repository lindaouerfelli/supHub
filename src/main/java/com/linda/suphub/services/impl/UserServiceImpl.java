package com.linda.suphub.services.impl;

import com.linda.suphub.config.JwtUtils;
import com.linda.suphub.dto.AuthenticationRequest;
import com.linda.suphub.dto.AuthenticationResponse;
import com.linda.suphub.dto.UserDto;
import com.linda.suphub.models.Role;
import com.linda.suphub.services.UserService;


//import com.linda.suphub.config.JwtUtils;

import com.linda.suphub.models.User;

import com.linda.suphub.repositories.RoleRepository;
import com.linda.suphub.repositories.UserRepository;
import com.linda.suphub.validators.ObjectsValidator;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;


/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;*/


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ObjectsValidator<UserDto> validator;

    private  final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;
    private  final AuthenticationManager authManager;
    private static final String ROLE_USER = "ROLE_USER";
    private final RoleRepository roleRepository;

    @Override
    public Integer save(UserDto dto) {

        validator.validate(dto);
        User user = UserDto.toEntity(dto);

        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user).getId();
    }

    @Override
    @Transactional
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                //.map(u -> UserDto.fromEntity(u))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found withe ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
        repository.deleteById(id);


    }

    @Override
    @Transactional

    public Integer validateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO USER WAS FOUND FOR USER ACCOUNT VALIDATION"));


        user.setActive(true);
        repository.save(user);
        return user.getId();    }

    @Override
    public Integer invalidateAccount(Integer id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO USER WAS FOUND FOR USER ACCOUNT VALIDATION"));

        user.setActive(false);
        repository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole(findOrCreateRole(ROLE_USER));

        var savedUser = repository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullName", savedUser.getFirstname() + " " + savedUser.getLastname());
        String token = jwtUtils.generateToken(savedUser, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //ceci ca permet d'authentifier un user pour voir sil est valide ou non !
        // je dois deleguer totue lopération d'authentificationa spring et qui peut faire ca ? ---> authenticationManager de SecurityConfig

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        ); // cette partie va essayer d'authentifier luser une fois le user est authentifié on passer a l'étape suivante ---> sinon il valever une exception !on va lajouter au niveau de notre gestionnaire dexeecption


        final User user = repository.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullName", user.getFirstname() + " " + user.getLastname());
        final String token = jwtUtils.generateToken(user, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName) // oncherhcer le rol depusi la bd si on l'a
                .orElse(null);
        if (role == null) {
            return roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }
        return role;
    }

}
