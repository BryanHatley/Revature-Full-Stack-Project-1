package com.revature.DTOs;


import com.revature.Models.Reimbursement;

public class OutgoingReimbursementDTO
{
    private int reimbursementID;
    private String description;
    private double amount;
    private String status;
    private OutgoingUserDTO outgoingUserDTO;

    // boilerplate------------------------


    public OutgoingReimbursementDTO() {
    }

    public OutgoingReimbursementDTO(int reimbursementID, String description, double amount,
                                    String status, OutgoingUserDTO outgoingUserDTO) {
        this.reimbursementID = reimbursementID;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.outgoingUserDTO = outgoingUserDTO;
    }

    public OutgoingReimbursementDTO(Reimbursement r)
    {
        this.reimbursementID = r.getReimbursementID();
        this.description = r.getDescription();
        this.amount = r.getAmount();
        this.status = r.getStatus();
        this.outgoingUserDTO = new OutgoingUserDTO(r.getUser());
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

    public OutgoingUserDTO getOutgoingUserDTO()
    {
        return outgoingUserDTO;
    }

    public void setOutgoingUserDTO(OutgoingUserDTO outgoingUserDTO)
    {
        this.outgoingUserDTO = outgoingUserDTO;
    }

    @Override
    public String toString()
    {
        return "OutgoingReimbursementDTO{" +
                "reimbursementID=" + reimbursementID +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", outgoingUserDTO=" + outgoingUserDTO +
                '}';
    }
}
