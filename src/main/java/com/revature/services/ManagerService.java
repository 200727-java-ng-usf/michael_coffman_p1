package com.revature.services;

import com.revature.DAO.ManagerDAO;
import com.revature.dtos.Principal;
import com.revature.models.Reimbursements;

import java.util.Set;

/**
 *  Services for user's with the MANAGER role
 */
public class ManagerService {

    private final ManagerDAO manDAO = new ManagerDAO();

    /**
     * Retreives all reimbursements in the server with PENDING status
     * @return Set<Reimbursements>
     */
    public Set<Reimbursements> getAllReimbursements() {

        try {
            Set<Reimbursements> reimbursements = manDAO.getAllReimbursements();
            return reimbursements;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Attempts to update the new reimbursement status; returns boolean for client verification
     * @param reimbursement
     * @return boolean
     */
    public boolean approveOrDeny(Reimbursements reimbursement) {

        if (manDAO.approveOrDeny(reimbursement)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the reimbursement the manager has selected, so the incoming JSON data
     * can be applied to it to change status
     * @param id
     * @return
     */
    public Reimbursements getReimbursementById(int id) {
        Reimbursements reimbursement = manDAO.getReimbursementById(id);
        return reimbursement;
    }

}
