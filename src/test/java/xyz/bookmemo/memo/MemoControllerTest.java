package xyz.bookmemo.memo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MemoControllerTest {

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void testMemoController() {
    assertTrue(true);
  }

  @Test
  void testGetMemoListPage() {
    assertTrue(true);
  }

  @Test
  void testGetTitleMemoListPage() {
    assertTrue(true);
  }

  @Test
  void testPostTitleListPage() {
    assertTrue(true);
  }

  @Test
  void testGetCategoryMemoListPage() {
    assertTrue(true);
  }

  @Test
  void testGetMemoCreatePage() {
    assertTrue(true);
  }

  @Test
  void testPostMemoCreatePage() {
    assertTrue(true);
  }

  @Test
  void testGetMemoDetailsPage() {
    assertTrue(true);
  }

  @Test
  void testPutMemoUpdatePage() {
    assertTrue(true);
  }

  @Test
  void testDeleteMemo() {
    assertTrue(true);
  }
}
