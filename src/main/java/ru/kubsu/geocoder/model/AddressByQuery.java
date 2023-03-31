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
public class AddressByQuery {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "query", nullable = false, unique = true) private String query;
  @Column(name = "address", nullable = true, unique = false) private String address;
  @Column(name = "latitude", nullable = true, unique = false) private Double latitude;
  @Column(name = "longitude", nullable = true, unique = false) private Double longitude;

  public AddressByQuery(final Integer id,
                        final String query,
                        final String address,
                        final Double latitude,
                        final Double longitude) {
    this.id = id;
    this.query = query;
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;
  }
  public AddressByQuery() {
    this.id = null;
    this.query = null;
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

  public String getQuery() {
    return query;
  }

  public void setQuery(final String query) {
    this.query = query;
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
    final AddressByQuery address1 = (AddressByQuery) o;
    return Objects.equals(id, address1.id)
      && Objects.equals(query, address1.query)
      && Objects.equals(address, address1.address)
      && Objects.equals(latitude, address1.latitude)
      && Objects.equals(longitude, address1.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, query, address, latitude, longitude);
  }

  @Override
  public String toString() {
    return "Address{"
      + "id=" + id
      + ", query='" + query + '\''
      + ", address='" + address + '\''
      + ", latitude=" + latitude
      + ", longitude=" + longitude
      + "}";
  }

  public static AddressByQuery of(final NominatimPlace place, final String query) {
    return new AddressByQuery(null, query, place.displayName(), place.latitude(), place.longitude());
  }
}
