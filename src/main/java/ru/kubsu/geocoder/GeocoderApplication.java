/**
 * Copyright 2023 Alekhin Aleksandr
 */
package ru.kubsu.geocoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ALekhin Aleksandr
 */
@SpringBootApplication
@SuppressWarnings({"PMD.UseUtilityClass", "PMD.HideUtilityClassConstructor"})
public class GeocoderApplication {
  public static void main(final String[] args) {
    SpringApplication.run(GeocoderApplication.class, args);
  }
}
