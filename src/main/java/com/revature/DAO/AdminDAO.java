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
                         "ON eu.user_role_id = ur.role_id" +
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

            PreparedStatement statement = conn.prepareStatement(sql);
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

            // Add all the mapped data into a Set<AppUser> object
            user.add(temp);
        }

        return user;

    }
}
