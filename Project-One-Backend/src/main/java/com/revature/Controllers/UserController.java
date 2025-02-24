package com.revature.Controllers;

import com.revature.Annotations.ManagerOnly;
import com.revature.DTOs.OutgoingUserDTO;
import com.revature.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    @ManagerOnly
    public ResponseEntity<List<OutgoingUserDTO>> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{userId}")
    @ManagerOnly
    public ResponseEntity<String> deleteUser(@PathVariable int userId)
    {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User successfully deleted.");
    }
}
