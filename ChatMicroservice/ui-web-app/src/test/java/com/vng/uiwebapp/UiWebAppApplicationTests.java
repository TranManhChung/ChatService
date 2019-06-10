package com.vng.uiwebapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({GrpcClientTest.class})
public class UiWebAppApplicationTests {

}
