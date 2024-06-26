package com.swygbr.backend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swygbr.backend.login.auth.JwtUserPrincipal;
import com.swygbr.backend.user.dto.UserRequestDto;
import com.swygbr.backend.user.dto.UserResponseDto;
import com.swygbr.backend.user.dto.UserSkillResponseDto;
import com.swygbr.backend.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<EntityModel<UserResponseDto>> getUserById(
            @AuthenticationPrincipal JwtUserPrincipal userPrincipal) {
        return ResponseEntity.ok(userService.findUserById(userPrincipal.getUserId()));
    }

    @GetMapping("/skill")
    public ResponseEntity<CollectionModel<UserSkillResponseDto>> getUserSkill(
            @AuthenticationPrincipal JwtUserPrincipal userPrincipal) {
        return ResponseEntity.ok(userService.findUserSkill(userPrincipal.getUserId()));
    }

    @PutMapping
    public ResponseEntity<?> putUserById(@AuthenticationPrincipal JwtUserPrincipal userPrincipal,
            @RequestBody UserRequestDto requestDto) {
        userService.updateUserById(userPrincipal.getUserId(), requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@AuthenticationPrincipal JwtUserPrincipal userPrincipal) {
        userService.deleteUser(userPrincipal.getUserId());
        return ResponseEntity.noContent().build();
    }
}
