package com.revature.DAO;

import com.revature.models.AppUser;
import com.revature.models.Role;
import com.revature.utils.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AdminDAO {

    public Optional<AppUser> findUserByUsername(String username) {

        Optional<AppUser> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM project1.ers_users eu " +
                         "JOIN project1.ers_user_roles ur " +
                         "ON eu.user_role_id = ur.role_id " +
                         "WHERE username = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return _user;
    }

    public void save(AppUser newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project1.ers_users (username, password, first_name, last_name, email, user_role_id) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql, new String[]{"ers_user_id", "status"});
            statement.setString(1, newUser.getUsername());
            statement.setString(2, newUser.getPassword());
            statement.setString(3, newUser.getFirstName());
            statement.setString(4, newUser.getLastName());
            statement.setString(5, newUser.getEmail());
            statement.setInt(6, newUser.getRole().ordinal() + 1);

            statement.executeUpdate();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public boolean updateUser(AppUser existingUser) {

        boolean updated = false;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "UPDATE project1.ers_users " +
                         "SET username = ?, " +
                             "password = ?, " +
                             "first_name = ?, " +
                             "last_name = ?, " +
                             "email = ?, " +
                             "status = ?, " +
                             "user_role_id = ? " +
                         "WHERE ers_user_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, existingUser.getUsername());
            statement.setString(2, existingUser.getPassword());
            statement.setString(3, existingUser.getFirstName());
            statement.setString(4, existingUser.getLastName());
            statement.setString(5, existingUser.getEmail());
            statement.setString(6, existingUser.getStatus());
            statement.setInt(7, existingUser.getRole().ordinal() + 1);
            statement.setInt(8, existingUser.getId());

            int temp = statement.executeUpdate();

            if (temp != 0) {
                return true;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return false;
    }

    public Set<AppUser> getAllUsers() {

        Set<AppUser> users = new HashSet<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM project1.ers_users eu " +
                         "JOIN project1.ers_user_roles ur " +
                         "ON eu.user_role_id = ur.role_id ";

            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql);
            users = mapResultSet(results);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return users;
    }


    private Set<AppUser> mapResultSet(ResultSet results) throws SQLException {

        Set<AppUser> user = new HashSet<>();

        while (results.next()) {
            AppUser temp = new AppUser();
            temp.setId(results.getInt("ers_user_id"));
            temp.setFirstName(results.getString("username"));
            temp.setLastName(results.getString("password"));
            temp.setUsername(results.getString("first_name"));
            temp.setPassword(results.getString("last_name"));
            temp.setEmail(results.getString("email"));
            temp.setRole(Role.getRoleName(results.getString("role_name")));
            temp.setStatus(results.getString("status"));

            // Add all the mapped data into a Set<AppUser> object
            user.add(temp);
        }

        return user;

    }
}
