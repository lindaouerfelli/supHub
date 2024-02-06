package com.linda.suphub.controllers;

import com.linda.suphub.config.JwtUtils;
import com.linda.suphub.dto.AuthenticationRequest;
import com.linda.suphub.dto.AuthenticationResponse;
import com.linda.suphub.dto.UserDto;
import com.linda.suphub.services.UserService;
//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
//@Tag(name = "authentication")
public class AuthenticationController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto user)

    {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) // ici on a cree un objet authenticationrequest qui contient suelement le usename et le password ou bien on pouvait appeler le userdto directement cat il contient le password et leusrname et dautres infos

    {
        return ResponseEntity.ok(userService.authenticate(request));
    }

}
