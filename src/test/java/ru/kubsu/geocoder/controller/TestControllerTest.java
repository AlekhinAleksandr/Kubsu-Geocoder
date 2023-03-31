package ru.kubsu.geocoder.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kubsu.geocoder.dto.RestApiError;
import ru.kubsu.geocoder.repository.TestRepository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerTest {
  @Autowired
  private TestRepository testRepository;
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
  void getTestIntegrationTest() {
    ResponseEntity<ru.kubsu.geocoder.model.Test> response = testRestTemplate.
      getForEntity(
        "http://localhost:" + port + "/tests/1?name=test",
        ru.kubsu.geocoder.model.Test.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    final ru.kubsu.geocoder.model.Test body = response.getBody();
    assertEquals(1, body.getId());
    assertEquals("test", body.getName());
    assertEquals(false, body.getDone());
    assertEquals(null, body.getMark());
  }
  @Test
  void getTestIntegrationTestWhenNameIsNull() {
    ResponseEntity<HashMap<String, String>> response = testRestTemplate
      .exchange("http://localhost:"+this.port+"/tests/test/id1", HttpMethod.GET, null,
        new ParameterizedTypeReference<HashMap<String, String>>() {});
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    Map<String, String> body = response.getBody();
    System.out.println(body);
    assertEquals("/tests/test/id1", body.get("path")); body.remove("path");
    assertEquals("Bad Request", body.get("error")); body.remove("error");
    assertEquals("400", body.get("status")); body.remove("status");
    body.remove("timestamp");
    assertEquals(true, body.isEmpty());
  }
  @Test
  void getTestIntegrationTestWhenIdIsString() {//NegativeTest
    ResponseEntity<RestApiError> response = testRestTemplate
      .exchange("http://localhost:"+this.port+"/tests/test/idabc?name=test", HttpMethod.GET, null,
        RestApiError.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    RestApiError body = response.getBody();
    System.out.println(body);
    assert body != null;
    assertEquals("/tests/test/idabc", body.path());
    assertEquals("Bad Request", body.error());
    assertEquals(400, body.status());
  }

  @Test
  void saveTestAddName() {
    testRestTemplate
      .exchange("http://localhost:"+this.port+"/tests/addTest?name=test", HttpMethod.GET, null,
        String.class);
    ResponseEntity<RestApiError> response = testRestTemplate
      .exchange("http://localhost:"+this.port+"/tests/addTest?name=test", HttpMethod.GET, null,
        RestApiError.class);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    RestApiError body= response.getBody();
    assertEquals("/tests/addTest", body.path());
    assertEquals("Internal Server Error", body.error());
    assertEquals(500, body.status());

    testRepository.deleteAll();
  }
  @Test
  void loadITestForNotName() {
    ResponseEntity<RestApiError> response = testRestTemplate
      .getForEntity("http://localhost:"+this.port+"/tests/getTest/test",
        RestApiError.class);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    RestApiError body= response.getBody();
    assertEquals("/tests/getTest/test", body.path());
    assertEquals("Internal Server Error", body.error());
    assertEquals(500, body.status());
  }

  @Test
  void helloTest() {
    ResponseEntity<HashMap> response = testRestTemplate
      .exchange("http://localhost:"+this.port+"/tests/hello",
        HttpMethod.GET, null,
        new ParameterizedTypeReference<HashMap>() {});
    HashMap body = response.getBody();
    if(response.getStatusCode()==HttpStatus.OK) {
      assertEquals("data", body.get("result"));
    } else if(response.getStatusCode()==HttpStatus.NOT_FOUND) {
      assertNotEquals(null, body);
    } else {
      throw new RuntimeException("expected StatusCode OK or NOT_FOUND instead of "+
        response.getStatusCode());
    }
  }
}
