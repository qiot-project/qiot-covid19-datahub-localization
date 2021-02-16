package io.qiot.covid19.datahub.localization.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import io.qiot.covid19.datahub.localization.client.NominatimServiceClient;
import io.qiot.covid19.datahub.localization.domain.dto.Location;

@ApplicationScoped
public class LocationService {

    private static final String COUNTRY = "country";
    private static final String COUNTRY_CODE = "country_code";
    private static final String CITY = "city";
    private static final String TOWN = "town";

    @Inject
    Logger LOGGER;

    @Inject
    @RestClient
    NominatimServiceClient serviceClient;

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

        if (jsonAddress.containsKey(COUNTRY)) {
            location.country = jsonAddress.getString(COUNTRY);
            location.ccode = jsonAddress.getString(COUNTRY_CODE);
        }

        return location;
    }
}
