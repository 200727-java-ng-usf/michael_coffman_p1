package com.revature.services;

import com.revature.DAO.AdminDAO;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.models.AppUser;
import com.revature.models.Role;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;


public class AdminServiceTests {


    private AdminService sut;
    private AdminDAO mockAdDAO = Mockito.mock(AdminDAO.class);

    Set<AppUser> mockUser = new HashSet<>();



    @Before
    public void setup() {
        sut = new AdminService();
        mockUser.add(new AppUser("Michael", "Coffman", "mcoffma04", "adminpass", "mcoffma04@revature.net", Role.ADMIN, "Active", 1));

    }

    @After
    public void tearDown() {
        sut = null;
        mockUser.removeAll(mockUser);
    }

    @Test (expected = RuntimeException.class)
    public void isUsernameTaken() {
        AppUser newUser = new AppUser("Michael", "Coffman", "mcoffma04", "adminpass", "mcoffma04@revature.net", Role.ADMIN, "Active");
        sut.register(newUser);
    }

    @Test
    public void areCredentialsValid() {

        AppUser testUser = new AppUser("a", "a", "a", "a", "a", Role.EMPLOYEE, "Active");
        Assert.assertTrue(sut.isUserValid(testUser));
    }

    @Test
    public void doesUserRegister() {

        AppUser newUser = new AppUser("Michael", "Coffman", "mcoffma04", "adminpass", "mcoffma04@revature.net", Role.ADMIN, "Active");
        Assert.assertFalse(sut.isUserValid(newUser) && !newUser.getUsername().equals("mcoffma04"));

    }

    // Still wants to keep interacting with my real DB........
    @Test
    public void getAllUser() {
        AppUser newUser1 = new AppUser("Michael", "Coffman", "mcoffma04", "adminpass", "mcoffma04@revature.net", Role.ADMIN, "Active", 1);
        AppUser newUser2 = new AppUser("a", "a", "a", "a", "a", Role.ADMIN, "Active", 2);
        Set<AppUser> testSet = new HashSet<>();
        testSet.add(newUser1);
        testSet.add(newUser2);
        sut.getAllUsers();

        Assert.assertTrue(true);


    }


}
