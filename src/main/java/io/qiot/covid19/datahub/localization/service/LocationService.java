package io.qiot.covid19.datahub.localization.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import io.qiot.covid19.datahub.localization.client.NominatimServiceClient;
import io.qiot.covid19.datahub.localization.domain.dto.Location;

/**
 * The Class LocationService.
 *
 * @author andreabattaglia
 */
@ApplicationScoped
public class LocationService {

    /** The Constant COUNTRY. */
    private static final String COUNTRY = "country";
    
    /** The Constant COUNTRY_CODE. */
    private static final String COUNTRY_CODE = "country_code";
    
    /** The Constant CITY. */
    private static final String CITY = "city";
    
    /** The Constant TOWN. */
    private static final String TOWN = "town";
    
    /** The Constant VILLAGE. */
    private static final String VILLAGE = "village";

    /** The logger. */
    @Inject
    Logger LOGGER;

    /** The service client. */
    @Inject
    @RestClient
    NominatimServiceClient serviceClient;

    /**
     * Translate coordinates.
     *
     * @param longitude the longitude
     * @param latitude the latitude
     * @return the location
     * @throws Exception the exception
     */
    public Location translateCoordinates(double longitude, double latitude)
            throws Exception {

        JsonObject jsonResult = serviceClient.reverse("json", longitude,
                latitude);

        LOGGER.debug("Json Object: {}", jsonResult.toString());

        JsonObject jsonAddress = jsonResult.getJsonObject("address");

        Location location = new Location();
        if (jsonAddress.containsKey(CITY))
            location.city = jsonAddress.getString(CITY);
        else if (jsonAddress.containsKey(TOWN))
            location.city = jsonAddress.getString(TOWN);
        else if (jsonAddress.containsKey(VILLAGE))
            location.city = jsonAddress.getString(VILLAGE);

        if (jsonAddress.containsKey(COUNTRY)) {
            location.country = jsonAddress.getString(COUNTRY);
            location.ccode = jsonAddress.getString(COUNTRY_CODE);
        }

        return location;
    }
}
