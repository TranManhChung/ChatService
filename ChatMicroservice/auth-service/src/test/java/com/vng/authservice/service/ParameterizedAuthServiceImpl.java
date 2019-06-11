//package com.vng.authservice;
//
//import com.vng.authservice.service.AuthServiceImpl;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.junit.runners.Parameterized.Parameters;
//import java.util.Arrays;
//import java.util.Collection;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(value = Parameterized.class)
//public class ParameterizedAuthServiceImpl {
//
//    private String input;
//    private String expected;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    public ParameterizedAuthServiceImpl(String number, String expected) {
//        this.input = number;
//        this.expected = expected;
//    }
//
//    @Parameters
//    public static Collection<Object[]> data() {
//        return Arrays.asList(new Object[][]{
//                {"chung", "chung"}, {null, null}, {"",""}
//        });
//    }
//
//    @Test
//    public void decodeToken() {
//
//        AuthServiceImpl authService = new AuthServiceImpl();
//        String token = authService.generateToken(input, input);
//        assertEquals(expected, authService.decodeToken(token).getClaim("username").asString());
//
//    }
//
//}

////@RunWith(value = Parameterized.class)
//public class ParameterizedAuthServiceImpl {
//
//    private static AuthServiceImpl authService;
//
//    @BeforeClass
//    public static void init(){
//
//        authService = new AuthServiceImpl();
//
//    }
//    @AfterClass
//    public static void destroy(){
//
//    }
//
//    @Test
//    public void decodeToken_NormalAccount() {
//
//
//        String token = authService.generateToken("normal_u_p","normal_u_p");
//        assertEquals("normal_u_p", authService.decodeToken(token).getClaim("username").asString());
//
//    }
//    @Test
//    public void decodeToken_NullAccount() {
//
//        String token = authService.generateToken(null, null);
//        assertEquals(null, authService.decodeToken(token).getClaim("username").asString());
//
//    }
//    @Test
//    public void decodeToken_EmptyAccount() {
//
//        String token = authService.generateToken("", "");
//        assertEquals("", authService.decodeToken(token).getClaim("username").asString());
//
//    }
//
//
//}