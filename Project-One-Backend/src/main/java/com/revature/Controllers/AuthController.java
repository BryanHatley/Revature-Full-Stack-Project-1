package com.revature.Controllers;

import com.revature.Models.Employee;
import com.revature.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//TODO: Request Mapping
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
    public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee)
    {
        //Send user data to service to create account
        Employee createdEmployee = authService.registerEmployee(employee);

        //Return creatred user as JSON
        return  ResponseEntity.ok(createdEmployee);
    }
}
