/**
 * Copyright 2023 Alekhin Aleksandr
 */
package ru.kubsu.geocoder.dto;

/**
 * Error.
 *
 * @param status status
 * @param error error
 * @param path path
 */
public record RestApiError(
  Integer status,
  String error,
  String path

) {
  public RestApiError() {
    this(0, "", "");
  }
}
