package com.revature.services;

import com.revature.DAO.EmployeeDAO;
import com.revature.dtos.Principal;
import com.revature.models.AppUser;
import com.revature.models.Reimbursements;

import java.util.Set;

public class EmployeeService {

    private final EmployeeDAO empDAO = new EmployeeDAO();

    public boolean submitReimburse(Reimbursements newReimburse) {

        if (empDAO.submitReimburse(newReimburse)) {
            return true;
        }
        return false;
    }

    public Set<Reimbursements> getUsersReimbursements(Principal currentUserReimbursements) {

        try {
            Set<Reimbursements> reimbursements = empDAO.currentUserReimbursements(currentUserReimbursements);
            return reimbursements;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateReimbursement(Reimbursements reimbursements) {

        try {
            if (empDAO.updateReimbursement(reimbursements)) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
