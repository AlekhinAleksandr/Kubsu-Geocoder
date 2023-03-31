/**
 * Copyright 2023 Alekhin Aleksandr
 */



package ru.kubsu.geocoder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kubsu.geocoder.dto.NominatimPlace;

import java.util.List;
import java.util.Optional;

/**
 * @author Alekhin Aleksandr
 */
@FeignClient(name = "NominatimClient", url = "https://nominatim.openstreetmap.org")
public interface NominatimClient {

  String JSON_FORMAT = "json";

  @RequestMapping(method = RequestMethod.GET, value = "/search", produces = "application/json")
  List<NominatimPlace> search(@RequestParam("q") String query, @RequestParam("format") String format);

  /**
   * Поиск объекта на карте по адрессной строке в свободном формате.
   * В случе нескольких подходящих объектов, будет возвращен самый релевантный.
   *
   * @param query Строка поиска.
   * @return Объект адресса.
   */
  default Optional<NominatimPlace> search(final String query) {
    try {
      return Optional.of(search(query, JSON_FORMAT).get(0));
    } catch (Exception ex) {
      return Optional.empty();
    }
  }

  /**
   * получение аддресса по кординатам.
   *
   * @param latitude долгота
   * @param longitude ширина
   * @param format формат
   * @return обьект аддресса
   */
  @RequestMapping(method = RequestMethod.GET, value = "/reverse", produces = "application/json")
  NominatimPlace reverse(@RequestParam("lat") Double latitude,
                         @RequestParam("lon") Double longitude,
                         @RequestParam("format") String format);
  /**
   * поиск обьекта аддресса по координатам.
   *
   * @param latitude долгота
   * @param longitude ширина
   * @return обьект аддресса
   */
  default Optional<NominatimPlace> reverse(final @RequestParam("lat") Double latitude,
                                           final @RequestParam("lon") Double longitude) {
    try {
      return Optional.of(reverse(latitude, longitude, JSON_FORMAT));
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
