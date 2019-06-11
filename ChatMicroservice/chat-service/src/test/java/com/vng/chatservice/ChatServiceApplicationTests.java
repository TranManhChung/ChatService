package com.vng.chatservice;

import com.vng.chatservice.controller.MessageControllerTest;
import com.vng.chatservice.repositories.MessageRepositoryTest;
import com.vng.chatservice.repositories.RoomRepositoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MessageRepositoryTest.class, RoomRepositoryTest.class, MessageControllerTest.class})
public class ChatServiceApplicationTests {

}
