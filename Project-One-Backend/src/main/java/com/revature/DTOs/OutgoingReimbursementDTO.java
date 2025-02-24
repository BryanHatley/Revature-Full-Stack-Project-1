package com.revature.DTOs;


import com.revature.Models.Reimbursement;

public class OutgoingReimbursementDTO
{
    private int reimbursementID;
    private String description;
    private double amount;
    private String status;
    private int userId;

    // boilerplate------------------------


    public OutgoingReimbursementDTO() {
    }

    public OutgoingReimbursementDTO(int reimbursementID, String description, double amount,
                                    String status, int userId) {
        this.reimbursementID = reimbursementID;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
    }

    public OutgoingReimbursementDTO(Reimbursement r)
    {
        this.reimbursementID = r.getReimbursementID();
        this.description = r.getDescription();
        this.amount = r.getAmount();
        this.status = r.getStatus();
        this.userId = r.getUser().getUserId();
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

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
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

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Override
    public String toString()
    {
        return "OutgoingReimbursementDTO{" +
                "reimbursementID=" + reimbursementID +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }
}
