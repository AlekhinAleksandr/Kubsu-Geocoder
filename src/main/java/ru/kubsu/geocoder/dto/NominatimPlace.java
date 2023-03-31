/**
 * Copyright 2023 Alekhin Aleksandr
 */
package ru.kubsu.geocoder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Place.
 *
 * @param latitude latitude
 * @param longitude longitude
 * @param displayName displayName
 * @param type type
 */
public record NominatimPlace(
  @JsonProperty("lat") Double latitude,
  @JsonProperty("lon") Double longitude,
  @JsonProperty("display_name") String displayName,
  @JsonProperty("type") String type
) {
  public NominatimPlace() {
    this(0.0, 0.0, "", "");
  }
}
