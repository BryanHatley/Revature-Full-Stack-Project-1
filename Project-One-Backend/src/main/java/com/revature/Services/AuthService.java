package com.revature.Services;

import com.revature.DAOs.EmployeeDAO;
import com.revature.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    private final EmployeeDAO employeeDAO;

    @Autowired

    public AuthService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public User registerEmployee(User user)
    {
        //TODO: input validation

        return employeeDAO.save(user);
    }
}
