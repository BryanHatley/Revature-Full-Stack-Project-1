package com.revature.Aspects;

import com.revature.Exceptions.UnauthorizedUserException;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthAspect
{
    @Order(1)
    @Before("within(com.revature.Controllers.*)" +
            "&& !within(com.revature.Controllers.AuthController)")
    public void checkLoggedIn()
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(false);

        if(session == null || session.getAttribute("userId") == null)
        {
            throw new UnauthorizedUserException("User must be logged in to do this!");
        }
    }

    @Order(2)
    @Before("@annotation(com.revature.Annotations.ManagerOnly)")
    public void checkManager()
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(false);

//        if (session == null)
//        {
//            throw new UnauthorizedUserException("User must be logged in to do this!");
//        }

        String role = session.getAttribute("role").toString();

        if(!role.equals("manager"))
        {
            throw new UnauthorizedUserException("User must be a Manager to do this!");
        }
    }
}
