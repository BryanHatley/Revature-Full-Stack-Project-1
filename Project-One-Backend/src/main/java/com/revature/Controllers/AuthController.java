package com.revature.Controllers;

import com.revature.DTOs.OutgoingUserDTO;
import com.revature.Models.User;
import com.revature.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
//@CrossOrigin
public class AuthController
{
    private final AuthService authService;

    @Autowired

    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<OutgoingUserDTO> registerEmployee(@RequestBody User user)
    {
        //Send user data to service to create account
        OutgoingUserDTO createdUser = authService.registerEmployee(user);

        //Return created user as JSON
        return  ResponseEntity.ok(createdUser);
    }
}
