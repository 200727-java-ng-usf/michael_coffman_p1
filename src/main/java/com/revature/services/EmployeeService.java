package com.revature.services;

import com.revature.DAO.EmployeeDAO;
import com.revature.dtos.Principal;
import com.revature.models.AppUser;
import com.revature.models.Reimbursements;

import java.util.Set;

/**
 *  Encapsulates all the methods used by an Employee
 */
public class EmployeeService {

    private final EmployeeDAO empDAO = new EmployeeDAO();

    /**
     * Takes in a Reimbursement and saves the information to the server
     * @param newReimburse
     * @return boolean
     */
    public boolean submitReimburse(Reimbursements newReimburse) {

        if (empDAO.submitReimburse(newReimburse)) {
            return true;
        }
        return false;
    }

    /**
     * Retrieves all the reimbursements based on the current session's user
     * @param currentUserReimbursements
     * @return Set<Reimbursements>
     */
    public Set<Reimbursements> getUsersReimbursements(Principal currentUserReimbursements) {

        try {
            Set<Reimbursements> reimbursements = empDAO.currentUserReimbursements(currentUserReimbursements);
            return reimbursements;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes in a Reimbursements and updates a Reimbursement belonging to the same
     * session user's Id and specified reimbursement Id
     * @param reimbursements
     * @return
     */
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
