package org.example.spring_boot.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring_boot.dto.request.PasswordChangeRequest;
import org.example.spring_boot.dto.response.AuthResponse;
import org.example.spring_boot.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/me/password")
    public ResponseEntity<AuthResponse> changePassword(
            @RequestBody PasswordChangeRequest dto,
            Authentication authentication
    ) {
       AuthResponse response = userService.changePassword(
                authentication.getName(),
                dto.getOldPassword(),
                dto.getNewPassword()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/active/{username}")
    public ResponseEntity<?> toggleActiveStatus(@PathVariable String username){
        boolean status = userService.toggleUserActiveStatus(username);
        return ResponseEntity.status(HttpStatus.OK).body("Status changed to " + status);
    }
}
