package ru.kubsu.geocoder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kubsu.geocoder.client.NominatimClient;
import ru.kubsu.geocoder.model.AddressByQuery;
import ru.kubsu.geocoder.model.AddressByCoordinates;
import ru.kubsu.geocoder.repository.AddressByQueryRepository;
import ru.kubsu.geocoder.repository.AddressByCoordinatesRepository;

import java.util.Optional;

/**
 * @author Alekhin Alelsandr
 */
@Service
public class AddressService {
  private final NominatimClient nominatimClient;
  private final AddressByQueryRepository addressByQueryRepository;
  private final AddressByCoordinatesRepository addressByCoordinatesRepository;

  @Autowired
  public AddressService(final NominatimClient nominatimClient,
                        final AddressByQueryRepository addressByQueryRepository,
                        final AddressByCoordinatesRepository addressByCoordinatesRepository) {
    this.nominatimClient = nominatimClient;
    this.addressByQueryRepository = addressByQueryRepository;
    this.addressByCoordinatesRepository = addressByCoordinatesRepository;
  }

  public Optional<AddressByQuery> search(final String query) {
    return addressByQueryRepository.findByQuery(query)
      .or(() -> nominatimClient.search(query)
        .map(p -> addressByQueryRepository.save(AddressByQuery.of(p, query)))
      );
  }

  public Optional<AddressByCoordinates> reverse(final Double latitude, final Double longitude) {
    return addressByCoordinatesRepository.findByInLatitudeAndInLongitude(latitude, longitude)
      .or(() -> nominatimClient.reverse(latitude, longitude)
        .map(p -> addressByCoordinatesRepository.save(AddressByCoordinates.of(p, latitude, longitude)))
      );
  }
}
