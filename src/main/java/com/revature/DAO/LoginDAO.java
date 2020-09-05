package com.revature.DAO;

import com.revature.models.*;
import com.revature.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LoginDAO {

    public Optional<AppUser> findUserByLogin(String username, String password) {

        Optional<AppUser> opUser = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {


            String query = "SELECT * FROM project1.ers_users eu " +
                           "JOIN project1.ers_user_roles eur " +
                           "ON eu.user_role_id = eur.role_id " +
                           "WHERE username = ? AND password = ?";


            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            opUser = mapResultSet(result).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return opUser;
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
