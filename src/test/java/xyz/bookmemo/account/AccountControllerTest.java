package xyz.bookmemo.account;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void testAcoountController() {
    assertTrue(true);
  }

  @Test
  void testGetTopPage() throws Exception {
    mockMvc.perform(get("/")).andExpect(status().isOk());
  }

  @Test
  void testGetLoginPage() throws Exception {
    mockMvc.perform(get("/login")).andExpect(status().isOk());
  }

  @Test
  void testPostLogin() {
    assertTrue(true);
  }

  @Test
  void testGetSignupPage() throws Exception {
    mockMvc.perform(get("/signup")).andExpect(status().isOk());
  }

  @Test
  void testPostSignup() {
    assertTrue(true);
  }

  @Test
  void testGetLogout() throws Exception {
    mockMvc.perform(get("/logout")).andExpect(status().isOk());
  }

  @Test
  void testGetProfile() throws Exception {
    mockMvc.perform(get("/profile")).andExpect(status().isOk());
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
