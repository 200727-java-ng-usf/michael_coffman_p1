package com.revature.services;

import com.revature.DAO.ManagerDAO;
import com.revature.dtos.Principal;
import com.revature.models.Reimbursements;

import java.util.Set;

public class ManagerService {

    private final ManagerDAO manDAO = new ManagerDAO();

    public Set<Reimbursements> getAllReimbursements() {

        try {
            Set<Reimbursements> reimbursements = manDAO.getAllReimbursements();
            return reimbursements;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean approveOrDeny(Reimbursements reimbursement) {

        if (manDAO.approveOrDeny(reimbursement)) {
            return true;
        } else {
            return false;
        }
    }

    public Reimbursements getReimbursementById(int id) {
        Reimbursements reimbursement = manDAO.getReimbursementById(id);
        return reimbursement;
    }

}
