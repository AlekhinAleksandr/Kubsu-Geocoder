package ru.kubsu.geocoder.client;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kubsu.geocoder.dto.NominatimPlace;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NominatimClientTest {
  @LocalServerPort
  private Integer port;
  private final TestRestTemplate testRestTemplate = new TestRestTemplate();

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
  void nomanatimClientIntegrationTest() {
    ResponseEntity<NominatimPlace> response = testRestTemplate
      .getForEntity("https://nominatim.openstreetmap.org/reverse?lat=45.019634&lon=39.031161&format=json",
        NominatimPlace.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    NominatimPlace body= response.getBody();
    if(body == null) {
      return;
    }
  }
}
