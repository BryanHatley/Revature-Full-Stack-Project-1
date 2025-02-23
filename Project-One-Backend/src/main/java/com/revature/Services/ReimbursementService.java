package com.revature.Services;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.DAOs.UserDAO;
import com.revature.DTOs.IncomingReimbursementDTO;
import com.revature.DTOs.OutgoingReimbursementDTO;
import com.revature.Models.Reimbursement;
import com.revature.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReimbursementService
{
    private final ReimbursementDAO reimbursementDAO;
    private final UserDAO userDAO;

    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDAO, UserDAO userDAO)
    {
        this.reimbursementDAO = reimbursementDAO;
        this.userDAO = userDAO;
    }

    public List<OutgoingReimbursementDTO> getAllReimbursements()
    {
        List<Reimbursement> reimb = reimbursementDAO.findAll();

        List<OutgoingReimbursementDTO> reimbursementDTOS = new ArrayList<>();

        for (Reimbursement r : reimb)
        {
            reimbursementDTOS.add(new OutgoingReimbursementDTO(r));
        }

        return reimbursementDTOS;
    }

    public List<OutgoingReimbursementDTO> getReimbursementsByStatus(String status)
    {
        List<Reimbursement> reimb = reimbursementDAO.findByStatus(status);

        List<OutgoingReimbursementDTO> reimbursementDTOS = new ArrayList<>();

        for (Reimbursement r : reimb)
        {
            reimbursementDTOS.add(new OutgoingReimbursementDTO(r));
        }

        return reimbursementDTOS;
    }

    public List<OutgoingReimbursementDTO> getAllReimbursementsByUser(int userId)
    {
        List<Reimbursement> reimb = reimbursementDAO.findByUser_UserId(userId);

        List<OutgoingReimbursementDTO> reimbursementDTOS = new ArrayList<>();

        for (Reimbursement r : reimb)
        {
            reimbursementDTOS.add(new OutgoingReimbursementDTO(r));
        }

        return reimbursementDTOS;
    }

    public List<OutgoingReimbursementDTO> getReimbursementsByStatusAndUser(String status, int userId)
    {
        List<Reimbursement> reimb = reimbursementDAO.findByStatusAndUser_UserId(status, userId);

        List<OutgoingReimbursementDTO> reimbursementDTOS = new ArrayList<>();

        for (Reimbursement r : reimb)
        {
            reimbursementDTOS.add(new OutgoingReimbursementDTO(r));
        }

        return reimbursementDTOS;
    }

    public OutgoingReimbursementDTO insertReimbursement(IncomingReimbursementDTO reimbursementDTO)
    {
        Reimbursement reimbursement = new Reimbursement(
                0,
                reimbursementDTO.getDescription(),
                reimbursementDTO.getAmount(),
                reimbursementDTO.getStatus(),
                null
        );

        Optional<User> user = userDAO.findById(reimbursementDTO.getUserId());

        if(user.isEmpty())
        {
            throw new IllegalArgumentException("User does not exist");
        }
        else
        {
            reimbursement.setUser(user.get());
        }

        return new OutgoingReimbursementDTO(reimbursementDAO.save(reimbursement));
    }

    public OutgoingReimbursementDTO updateReimbursementStatus(String status, int rId)
    {
         Reimbursement returnedReimbursement = reimbursementDAO.findById(rId).orElse(null);

         if(returnedReimbursement == null)
         {
             throw new IllegalArgumentException("Reimbursement does not exist!");
         }
         else
         {
             returnedReimbursement.setStatus(status);
             reimbursementDAO.save(returnedReimbursement);
         }

         return new OutgoingReimbursementDTO(returnedReimbursement);
    }

}
