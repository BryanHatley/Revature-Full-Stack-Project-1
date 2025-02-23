package com.revature.Services;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.DAOs.UserDAO;
import com.revature.DTOs.OutgoingUserDTO;
import com.revature.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService
{
    private final UserDAO userDAO;
    private final ReimbursementDAO reimbursementDAO;

    @Autowired
    public UserService(UserDAO userDAO, ReimbursementDAO reimbursementDAO)
    {
        this.userDAO = userDAO;
        this.reimbursementDAO = reimbursementDAO;
    }

    public List<OutgoingUserDTO> getAllUsers()
    {
        List<User> returnedUsers = userDAO.findAll();

        List<OutgoingUserDTO> userDTOS = new ArrayList<>();

        for(User u : returnedUsers)
        {
            userDTOS.add(new OutgoingUserDTO(u));
        }

        return userDTOS;
    }

    @Transactional(rollbackFor = SQLException.class)
    public void deleteUserById(int userId)
    {
        reimbursementDAO.deleteByUser_UserId(userId);
        userDAO.deleteById(userId);
    }
}
