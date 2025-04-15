// package com.example.nearbybanks.geo;

// public class GeoService {
    
// }
package com.example.nearbybanks.geo;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    public List<BankLocation> findNearbyBanks(String zipcode, double radiusMiles) throws Exception {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

        // Convert zipcode to lat/lng
        GeocodingResult[] geoResults = GeocodingApi.geocode(context, zipcode).await();
        if (geoResults.length == 0) {
            throw new Exception("Invalid zipcode");
        }
        LatLng location = geoResults[0].geometry.location;

        // Use PlacesApi.nearbySearchQuery directly without NearbySearchRequest type
        PlacesSearchResponse places = PlacesApi.nearbySearchQuery(context, location)
                .radius((int) (radiusMiles * 1609.34)) // Miles to meters
                .type(PlaceType.BANK)
                .await();

        List<BankLocation> banks = new ArrayList<>();
        for (PlacesSearchResult result : places.results) {
            banks.add(new BankLocation(
                    result.name,
                    result.vicinity,
                    result.geometry.location.lat,
                    result.geometry.location.lng
            ));
        }
        return banks;
    }

    public static class BankLocation {
        private String name;
        private String address;
        private double latitude;
        private double longitude;

        public BankLocation(String name, String address, double latitude, double longitude) {
            this.name = name;
            this.address = address;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() { return name; }
        public String getAddress() { return address; }
        public double getLatitude() { return latitude; }
        public double getLongitude() { return longitude; }
    }
}