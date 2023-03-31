/**
 * Copyright 2023 Alekhin Aleksandr
 */
package ru.kubsu.geocoder.model;

import ru.kubsu.geocoder.dto.NominatimPlace;
import javax.persistence.*;
import java.util.Objects;

/**
 * @author Alekhin Aleksandr
 */
@SuppressWarnings("PMD.AvoidFieldNameMatchingTypeName")
@Entity
public class AddressByCoordinates {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "in_latitude", nullable = false, unique = false) private Double inLatitude;
  @Column(name = "in_longitude", nullable = false, unique = false) private Double inLongitude;
  @Column(name = "address", nullable = true, unique = false) private String address;
  @Column(name = "latitude", nullable = true, unique = false) private Double latitude;
  @Column(name = "longitude", nullable = true, unique = false) private Double longitude;

  public AddressByCoordinates(final Integer id,
                              final Double inLatitude,
                              final Double inLongitude,
                              final String address,
                              final Double latitude,
                              final Double longitude) {
    this.id = id;
    this.inLatitude = inLatitude;
    this.inLongitude = inLongitude;
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;
  }
  public AddressByCoordinates() {
    this.id = null;
    this.inLatitude = null;
    this.inLongitude = null;
    this.address = null;
    this.latitude = null;
    this.longitude = null;
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public Double getInLatitude() {
    return inLatitude;
  }

  public void setInLatitude(final Double inLatitude) {
    this.inLatitude = inLatitude;
  }

  public Double getInLongitude() {
    return inLongitude;
  }

  public void setInLongitude(final Double inLongitude) {
    this.inLongitude = inLongitude;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(final Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(final Double longitude) {
    this.longitude = longitude;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AddressByCoordinates address1 = (AddressByCoordinates) o;
    return Objects.equals(id, address1.id)
      && Objects.equals(inLatitude, address1.inLatitude)
      && Objects.equals(inLongitude, address1.inLongitude)
      && Objects.equals(address, address1.address)
      && Objects.equals(latitude, address1.latitude)
      && Objects.equals(longitude, address1.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, inLatitude, inLongitude, address, latitude, longitude);
  }

  @Override
  public String toString() {
    return "Address{"
      + "id=" + id
      + ", inLatitude=" + inLatitude
      + ", inLongitude=" + inLongitude
      + ", address='" + address + '\''
      + ", latitude=" + latitude
      + ", longitude=" + longitude
      + "}";
  }

  public static AddressByCoordinates of(final NominatimPlace place,
                                        final Double inLatitude,
                                        final Double inLongitude) {
    return new AddressByCoordinates(null, inLatitude, inLongitude,
      place.displayName(), place.latitude(), place.longitude());
  }
}
