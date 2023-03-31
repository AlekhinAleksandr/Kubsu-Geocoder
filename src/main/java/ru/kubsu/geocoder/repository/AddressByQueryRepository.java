/**
 * Copyright 2023 Alekhin Aleksandr
 */
package ru.kubsu.geocoder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kubsu.geocoder.model.AddressByQuery;

import java.util.Optional;

/**
 * @author Alekhin Aleksandr
 */
@Repository
public interface AddressByQueryRepository extends CrudRepository<AddressByQuery, Integer> {
  Optional<AddressByQuery> findByQuery(String query);
  Optional<AddressByQuery> findByLatitudeAndLongitude(Double latitude, Double longitude);
}
