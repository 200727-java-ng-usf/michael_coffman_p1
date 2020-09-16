package com.revature.DAO;

import com.revature.dtos.Principal;
import com.revature.models.AppUser;
import com.revature.models.Reimbursements;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.utils.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDAO {

    /**
     * Submits a new reimbursement into the database.
     * @param newReimburse
     * @return boolean
     */
    public boolean submitReimburse(Reimbursements newReimburse) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project1.ers_reimbursements (amount, description, author_id, reimb_type_id) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql, new String[]{"reimb_id", "submitted", "reimb_status_id"});
            statement.setDouble(1, newReimburse.getAmount());
            statement.setString(2, newReimburse.getDescription());
            statement.setInt(3, newReimburse.getAuthorId());
            statement.setInt(4, newReimburse.getTypeId().ordinal() + 1);

            int temp = statement.executeUpdate();

            if (temp == 1) {
                return true;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    /**
     *  Updates an existing reimbursements specified by a user
     * @param reimbursement
     * @return boolean
     */
    public boolean updateReimbursement(Reimbursements reimbursement) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "UPDATE project1.ers_reimbursements " +
                         "SET amount = ?, " +
                         "description = ?, " +
                         "reimb_type_id = ? " +
                         "WHERE reimb_id = ? AND author_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, reimbursement.getAmount());
            statement.setString(2, reimbursement.getDescription());
            statement.setInt(3, reimbursement.getTypeId().ordinal() + 1);
            statement.setInt(4, reimbursement.getId());
            statement.setInt(5, reimbursement.getAuthorId());

            int temp = statement.executeUpdate();

            if (temp != 0) {
                return true;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return false;
    }

    /**
     *  Collects a Set of all reimbursements belonging to the currently logged-in user
     * @param currentUser
     * @return Set<Reimbursement>
     */
    public Set<Reimbursements> currentUserReimbursements(Principal currentUser) {

        Set<Reimbursements> reimbursements = new HashSet<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM project1.ers_reimbursements er " +
                         "JOIN project1.ers_users eu " +
                         "ON er.author_id = eu.ers_user_id " +
                         "JOIN project1.ers_reimbursement_statuses ers " +
                         "ON er.reimb_status_id = ers.reimb_status_id " +
                         "JOIN project1.ers_reimbursement_types ert " +
                         "ON er.reimb_type_id = ert.reimb_type_id " +
                         "WHERE er.author_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, currentUser.getId());

            ResultSet results = statement.executeQuery();
            reimbursements = mapResultSet(results);

            // Testing to make sure reimbursements are gathered as expected
            // System.out.println(reimbursements);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return reimbursements;
    }

    /**
     *  Maps the JDBC ResultSet into a Set<> of Reimbursement objects
     * @param results
     * @return Set<Reimbursement>
     * @throws SQLException
     */
    private Set<Reimbursements> mapResultSet(ResultSet results) throws SQLException {

        Set<Reimbursements> reimbursements = new HashSet<>();

        while (results.next()) {
            Reimbursements temp = new Reimbursements();
            temp.setId(results.getInt("reimb_id"));
            temp.setAmount(results.getDouble("amount"));
            temp.setSubmitted(results.getTimestamp("submitted"));
            temp.setResolved(results.getTimestamp("resolved"));
            temp.setDescription(results.getString("description"));
            temp.setResolverName(results.getString("resolver_id"));
            temp.setTypeId(Type.getType(results.getString("reimb_type")));
            temp.setStatusId(Status.getStatus(results.getString("reimb_status")));


            // Add all the mapped data into a Set<AppUser> object
            reimbursements.add(temp);
        }

        return reimbursements;

    }
}
