package xyz.bookmemo.account;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

  @Autowired MockMvc mockMvc;

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void testAcoountController() {
    assertTrue(true);
  }

  @Test
  void testGetTopPage() throws Exception {
    assertTrue(true);
  }

  @Test
  void testGetLoginPage() throws Exception {
    assertTrue(true);
  }

  @Test
  void testPostLogin() {
    assertTrue(true);
  }

  @Test
  void testGetSignupPage() throws Exception {
    assertTrue(true);
  }

  @Test
  void testPostSignup() {
    assertTrue(true);
  }

  @Test
  void testGetLogout() throws Exception {
    assertTrue(true);
  }

  @Test
  void testGetProfile() throws Exception {
    assertTrue(true);
  }

  @Test
  void testPostProfile() {
    assertTrue(true);
  }

  @Test
  void testDeleteUser() {
    assertTrue(true);
  }
}
