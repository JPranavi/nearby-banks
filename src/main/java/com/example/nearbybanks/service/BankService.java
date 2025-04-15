// package com.example.nearbybanks.service;

// public class BankService {
    
// }
package com.example.nearbybanks.service;

import com.example.nearbybanks.geo.GeoService;
import com.example.nearbybanks.geo.GeoService.BankLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private GeoService geoService;

    public List<BankLocation> getNearbyBanks(String zipcode) throws Exception {
        return geoService.findNearbyBanks(zipcode, 10.0); // 10-mile radius
    }
}