package com.revature.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "reimbursements")
public class Reimbursement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbursementID;

    private String description;

    private int amount;

    private String status;

    private User user;

    // boilerplate-----------------


    public Reimbursement()
    {
    }

    public Reimbursement(int reimbursementID, String description,
                         int amount, String status, User user)
    {
        this.reimbursementID = reimbursementID;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }

    public int getReimbursementID()
    {
        return reimbursementID;
    }

    public void setReimbursementID(int reimbursementID)
    {
        this.reimbursementID = reimbursementID;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "reimbursement{" +
                "reimbursementID=" + reimbursementID +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }
}
