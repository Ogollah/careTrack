package com.stephen.careTrack.helper;

import com.stephen.careTrack.DTO.LocationDTO;
import com.stephen.careTrack.model.Location;

/**
 * The `LocationMapper` class is responsible for mapping between Location and LocationDTO objects.
 * It provides static methods to convert Location objects to LocationDTOs and vice versa.
 *
 * This mapping class is essential for transferring location data between the data access layer and the presentation layer.
 *
 * @author Ogolla
 * @version 1.0
 * @see com.stephen.careTrack.DTO.LocationDTO
 * @see com.stephen.careTrack.model.Location
 */

public class LocationMapper {
    public static Location dtoToEntity(LocationDTO locationDTO)
    {
        Location location = new Location();
        location.setLongitude(locationDTO.getLongitude());
        location.setLatitude(locationDTO.getLatitude());
        location.setName(locationDTO.getName());
        location.setMfl(locationDTO.getMfl());
        return location;
    }

    public static LocationDTO entityToDTO(Location location)
    {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLongitude(location.getLongitude());
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setName(location.getName());
        locationDTO.setMfl(location.getMfl());
        return locationDTO;
    }
}
