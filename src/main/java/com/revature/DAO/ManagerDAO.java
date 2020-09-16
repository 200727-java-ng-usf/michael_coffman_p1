package com.revature.DAO;

import com.revature.dtos.Principal;
import com.revature.models.Reimbursements;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ManagerDAO {


    public Set<Reimbursements> getAllReimbursements() {

        Set<Reimbursements> reimbursements = new HashSet<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM project1.ers_reimbursements er " +
                    "JOIN project1.ers_users eu " +
                    "ON er.author_id = eu.ers_user_id " +
                    "JOIN project1.ers_reimbursement_statuses ers " +
                    "ON er.reimb_status_id = ers.reimb_status_id " +
                    "JOIN project1.ers_reimbursement_types ert " +
                    "ON er.reimb_type_id = ert.reimb_type_id " +
                    "WHERE ers.reimb_status_id = 1";

            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet results = statement.executeQuery();
            reimbursements = mapResultSetForTable(results);

            // Testing to make sure reimbursements are gathered as expected
            // System.out.println(reimbursements);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return reimbursements;
    }

    public Reimbursements getReimbursementById(int id) {

        Reimbursements reimbursements = new Reimbursements();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM project1.ers_reimbursements er " +
                         "WHERE reimb_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                reimbursements.setId(results.getInt("reimb_id"));
                reimbursements.setAmount(results.getDouble("amount"));
                reimbursements.setSubmitted(results.getTimestamp("submitted"));
                reimbursements.setResolved(results.getTimestamp("resolved"));
                reimbursements.setDescription(results.getString("description"));
                reimbursements.setAuthorId(results.getInt("author_id"));
                reimbursements.setResolverId(results.getInt("resolver_id"));
                reimbursements.setTypeId(Type.getType(results.getString("reimb_type_id")));
                reimbursements.setStatusId(Status.getStatus(results.getString("reimb_status_id")));
            }
            //reimbursements = mapResultSet(results);

            // Testing to make sure reimbursements are gathered as expected
            //System.out.println(reimbursements);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return reimbursements;
    }

    public boolean approveOrDeny(Reimbursements reimbursement) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "UPDATE project1.ers_reimbursements " +
                    "SET reimb_status_id = ?, " +
                    "resolver_id = ?, " +
                    "resolved = ? " +
                    "WHERE reimb_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, reimbursement.getStatusId().ordinal() + 1);
            statement.setInt(2, reimbursement.getResolverId());
            statement.setTimestamp(3, reimbursement.getResolved());
            statement.setInt(4, reimbursement.getId());

            int temp = statement.executeUpdate();

            if (temp != 0) {
                return true;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return false;
    }

    private Set<Reimbursements> mapResultSet(ResultSet results) throws SQLException {

        Set<Reimbursements> reimbursements = new HashSet<>();

        while (results.next()) {
            Reimbursements temp = new Reimbursements();
            temp.setId(results.getInt("reimb_id"));
            temp.setAmount(results.getDouble("amount"));
            temp.setSubmitted(results.getTimestamp("submitted"));
            temp.setResolved(results.getTimestamp("resolved"));
            temp.setDescription(results.getString("description"));
            temp.setAuthorId(results.getInt("author_id"));
            temp.setResolverId(results.getInt("resolver_id"));
            temp.setTypeId(Type.getType(results.getString("reimb_type_id")));
            temp.setStatusId(Status.getStatus(results.getString("reimb_status_id")));


            // Add all the mapped data into a Set<AppUser> object
            reimbursements.add(temp);
        }

        return reimbursements;

    }
    private Set<Reimbursements> mapResultSetForTable(ResultSet results) throws SQLException {

        Set<Reimbursements> reimbursements = new HashSet<>();

        while (results.next()) {
            Reimbursements temp = new Reimbursements();
            temp.setId(results.getInt("reimb_id"));
            temp.setAmount(results.getDouble("amount"));
            temp.setSubmitted(results.getTimestamp("submitted"));
            temp.setResolved(results.getTimestamp("resolved"));
            temp.setDescription(results.getString("description"));
            temp.setAuthorName(results.getString("username"));
            temp.setTypeId(Type.getType(results.getString("reimb_type")));
            temp.setStatusId(Status.getStatus(results.getString("reimb_status")));


            // Add all the mapped data into a Set<AppUser> object
            reimbursements.add(temp);
        }

        return reimbursements;

    }
}
