package com.vng.authservice;

import com.vng.authservice.google.GoogleUtilsTest;
import com.vng.authservice.repositories.UserRepositoryTest;
import com.vng.authservice.service.TestAuthServiceImpl;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestAuthServiceImpl.class, UserRepositoryTest.class, GoogleUtilsTest.class})
public class AuthServiceApplicationTests {

}