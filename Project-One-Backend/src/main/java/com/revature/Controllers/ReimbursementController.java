package com.revature.Controllers;

import com.revature.Annotations.ManagerOnly;
import com.revature.DTOs.IncomingReimbursementDTO;
import com.revature.DTOs.OutgoingReimbursementDTO;
import com.revature.Services.ReimbursementService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reimbursements")
public class ReimbursementController
{
    private final ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService)
    {
        this.reimbursementService = reimbursementService;
    }

    @GetMapping
    @ManagerOnly
    public ResponseEntity<List<OutgoingReimbursementDTO>> getAllReimbursements()
    {
        return ResponseEntity.ok(reimbursementService.getAllReimbursements());
    }

    @PostMapping
    public ResponseEntity<OutgoingReimbursementDTO> createReimbursement(@RequestBody IncomingReimbursementDTO incomingReimbursementDTO)
    {
        return ResponseEntity.accepted().body(reimbursementService.insertReimbursement(incomingReimbursementDTO));
    }

    @GetMapping("/pending")
    @ManagerOnly
    public ResponseEntity<List<OutgoingReimbursementDTO>> getPendingReimbursements()
    {
        return ResponseEntity.accepted().body(reimbursementService.getReimbursementsByStatus("pending"));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OutgoingReimbursementDTO>> getAllReimbursementsByUser(@PathVariable int userId)
    {
        return ResponseEntity.ok(reimbursementService.getAllReimbursementsByUser(userId));
    }

    @GetMapping("/pending/{userId}")
    @ManagerOnly
    public ResponseEntity<List<OutgoingReimbursementDTO>> getPendingReimbursementsByUser(@PathVariable int userId)
    {
        return ResponseEntity.accepted().body(reimbursementService.getReimbursementsByStatusAndUser("pending", userId));
    }

    @PatchMapping("/{reImbursementId}/{status}")
    @ManagerOnly
    public ResponseEntity<OutgoingReimbursementDTO> updateReimbursementStatusById(@PathVariable String status, @PathVariable int reImbursementId)
    {

        return ResponseEntity.ok(reimbursementService.updateReimbursementStatus(status, reImbursementId));
    }
}
