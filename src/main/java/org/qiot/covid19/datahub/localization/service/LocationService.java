package org.qiot.covid19.datahub.localization.service;

import java.io.StringReader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.qiot.covid19.datahub.localization.client.NominatimServiceClient;
import org.qiot.covid19.datahub.localization.domain.dto.Location;
import org.slf4j.Logger;

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
        Location location = null;
        String jsonResult = serviceClient.reverse("json", longitude, latitude);
        LOGGER.debug(jsonResult);
        location = new Location();
        try (StringReader sr = new StringReader(jsonResult);
                JsonReader reader = Json.createReader(sr)) {
            JsonObject jsonObject = reader.readObject();
            LOGGER.debug("Json Object: {}", jsonObject.toString());
            JsonObject jsonAddress = jsonObject.getJsonObject("address");
            LOGGER.debug("Json Address: {}", jsonAddress.toString());
            if (jsonAddress.containsKey(CITY))
                location.city = jsonAddress.getString(CITY);
            else if (jsonAddress.containsKey(TOWN))
                location.city = jsonAddress.getString(TOWN);
            if (jsonAddress.containsKey(COUNTRY)) {
                location.country = jsonAddress.getString(COUNTRY);
                location.ccode = jsonAddress.getString(COUNTRY_CODE);
            }
        }
        return location;
    }
}
