package ru.kubsu.geocoder.controller;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kubsu.geocoder.client.NominatimClient;
import ru.kubsu.geocoder.dto.NominatimPlace;
import ru.kubsu.geocoder.dto.RestApiError;
import ru.kubsu.geocoder.model.Test;
import ru.kubsu.geocoder.service.TestService;

import java.util.Random;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Alekhin Aleksandr
 */
@RestController
@RequestMapping("tests")
public class TestController {
  private final TestService service;
  private final NominatimClient nominatimClient;

  @Autowired
  public TestController(final TestService service, final NominatimClient nominatimClient) {
    this.service = service;
    this.nominatimClient = nominatimClient;
  }

  @GetMapping(value = "/test/id{id}", produces = APPLICATION_JSON_VALUE)
  public Test getTest(final @PathVariable Integer id, final @RequestParam String name) {
    return service.build(id, name);
  }

  @GetMapping(value = "/addTest", produces = APPLICATION_JSON_VALUE)
  public void save(final @RequestParam String name) {
    service.save(name);
  }

  @GetMapping(value = "/getTest/{name}", produces = APPLICATION_JSON_VALUE)
  public Test load(final @PathVariable String name) {
    return service.load(name);
  }

  @GetMapping(value = "/getLocationObjectByName", produces = APPLICATION_JSON_VALUE)
  public NominatimPlace getLocationObjectByName(final @RequestParam String name) {
    return nominatimClient.search(name, "json").get(0);
  }

  @GetMapping(value = "/getLocationObjectByCoordinate", produces = APPLICATION_JSON_VALUE)
  public NominatimPlace getLocationObjectByCoordinate(final @RequestParam Double latitude,
                                                      final @RequestParam Double longitude) {
    return nominatimClient.reverse(latitude, longitude, "json");
  }

  @GetMapping(value = "/hello", produces = APPLICATION_JSON_VALUE)
  @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
  @SuppressFBWarnings("DMI_RANDOM_USED_ONLY_ONCE")
  public ResponseEntity<?> hello() {
    final Random random = new Random();
    if (random.nextDouble() > 0.5) {
      final NominatimPlace place = new NominatimPlace();
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(place);
    } else {
      final RestApiError error = new RestApiError();
      return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(error);
    }
  }
}
