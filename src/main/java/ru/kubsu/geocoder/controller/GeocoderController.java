/**
 * Copyright 2023 Alekhin Aleksandr
 */
package ru.kubsu.geocoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kubsu.geocoder.model.AddressByQuery;
import ru.kubsu.geocoder.model.AddressByCoordinates;
import ru.kubsu.geocoder.service.AddressService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * @author Alekhin Aleksandr
 */
@RestController
@RequestMapping("geocoder")
public class GeocoderController {
  private final AddressService addressService;

  @Autowired
  public GeocoderController(final AddressService addressService) {
    this.addressService = addressService;
  }

  /**
   * получение адреса по имени.
   *
   * @param query аддресс
   * @return обьект аддресса
   */
  @GetMapping(value = "/getLocationObjectByName", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<AddressByQuery> getLocationObjectByName(final @RequestParam String query) {
    return addressService.search(query)
      .map(p -> ResponseEntity.status(HttpStatus.OK).body(p))
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  /**
   * получение аддресса по координакам.
   *
   * @param latitude ширина
   * @param longitude долгота
   * @return обьект аддресса
   */
  @GetMapping(value = "/getLocationObjectByCoordinates", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<AddressByCoordinates> getLocationObjectByCoordinates(
    final @RequestParam Double latitude, final @RequestParam Double longitude) {
    return addressService.reverse(latitude, longitude)
      .map(p -> ResponseEntity.status(HttpStatus.OK).body(p))
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }
}
