package ru.kubsu.geocoder.util;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TestUtilTest {
  @AfterAll
  static void afterAll() {
  }
  @BeforeAll
  static void beforeAll() {
  }

  @BeforeEach
  void setUp() {
  }
  @AfterEach
  void tearDown() {
  }

  @Test
  void test4() {
    Assertions.assertEquals(3, ru.kubsu.geocoder.util.TestUtil.sum(1,2));
    Assertions.assertEquals(300, ru.kubsu.geocoder.util.TestUtil.sum(99,201));
    Assertions.assertEquals(-7, ru.kubsu.geocoder.util.TestUtil.sum(-1,-6));
    Assertions.assertEquals(0, ru.kubsu.geocoder.util.TestUtil.sum(-4,4));
    Assertions.assertEquals(1, ru.kubsu.geocoder.util.TestUtil.sum(3000,-2999));
  }
}
