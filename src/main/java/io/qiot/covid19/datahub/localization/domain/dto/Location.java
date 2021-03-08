package io.qiot.covid19.datahub.localization.domain.dto;

import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * The Class Location.
 *
 * @author andreabattaglia
 */
@RegisterForReflection
public class Location implements Comparable<Location> {

    /** The city. */
    public String city;
    
    /** The country. */
    public String country;
    
    /** The ccode. */
    public String ccode;

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(city, country);
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        return Objects.equals(city, other.city)
                && Objects.equals(country, other.country);
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Location [city=");
        builder.append(city);
        builder.append(", country=");
        builder.append(country);
        builder.append(", country_code=");
        builder.append(ccode);
        builder.append("]");
        return builder.toString();
    }

    /**
     * Compare to.
     *
     * @param o the o
     * @return the int
     */
    @Override
    public int compareTo(Location o) {
        if (country.compareTo(o.country) != 0)
            return city.compareTo(o.city);
        return 0;
    }
}