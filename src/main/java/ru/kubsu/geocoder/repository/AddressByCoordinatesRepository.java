/**
 * Copyright 2023 Alekhin Aleksandr
 */
package ru.kubsu.geocoder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kubsu.geocoder.model.AddressByCoordinates;

import java.util.Optional;

/**
 * @author Alekhin Aleksandr
 */
@Repository
public interface AddressByCoordinatesRepository extends CrudRepository<AddressByCoordinates, Integer> {
  Optional<AddressByCoordinates> findByInLatitudeAndInLongitude(Double inLatitude, Double inLongitude);
}
