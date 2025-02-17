package com.revature.Services;

import com.revature.DAOs.EmployeeDAO;
import com.revature.Models.Employee;
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

    public Employee registerEmployee(Employee employee)
    {
        //TODO: input validation

        return employeeDAO.save(employee);
    }
}
