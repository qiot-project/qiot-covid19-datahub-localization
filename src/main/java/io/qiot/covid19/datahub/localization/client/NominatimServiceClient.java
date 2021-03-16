/**
 * 
 */

package io.qiot.covid19.datahub.localization.client;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * @author andreabattaglia
 *
 */
@Path("/")
@RegisterRestClient(configKey = "nominatim-api")
public interface NominatimServiceClient {

    @GET
    @Path("/reverse")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    JsonObject reverse(@QueryParam("format") String format,
            @QueryParam("lon") double lon, @QueryParam("lat") double lat,
            @QueryParam("accept-language") String language) throws Exception;

}
