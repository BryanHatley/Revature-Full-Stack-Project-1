package com.revature.Services;

import com.revature.DAOs.UserDAO;
import com.revature.DTOs.LoginDTO;
import com.revature.DTOs.OutgoingUserDTO;
import com.revature.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    private final UserDAO userDAO;

    @Autowired

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public OutgoingUserDTO registerEmployee(User user)
    {
        if (user.getUsername() == null || user.getUsername().isBlank())
        {
            throw new IllegalArgumentException("Username cannot be empty!");
        }
        else if (user.getPassword() == null || user.getPassword().isBlank())
        {
            throw new IllegalArgumentException("Password cannot be empty!");
        }
        else if (user.getPassword().length() < 8)
        {
            throw new IllegalArgumentException("Password cannot be shorter than 8 characters!");
        }
        else if (user.getFirstName().isBlank() || user.getLastName().isBlank())
        {
            throw new IllegalArgumentException("User must have a First and Last Name!");
        }


        return new OutgoingUserDTO(userDAO.save(user));
    }

    public OutgoingUserDTO login(LoginDTO loginDTO)
    {
        if (loginDTO.getUsername() == null || loginDTO.getUsername().isBlank())
        {
            throw new IllegalArgumentException("Username cannot be empty!");
        }
        else if (loginDTO.getPassword() == null || loginDTO.getPassword().isBlank())
        {
            throw new IllegalArgumentException("Password cannot be empty!");
        }

        User returnedUser = userDAO.findByUsernameAndPassword(loginDTO.getUsername(),
                loginDTO.getPassword()).orElse(null);

        if(returnedUser == null)
        {
            throw new IllegalArgumentException("Invalid username or password!");
        }

        return new OutgoingUserDTO(returnedUser);
    }
}
