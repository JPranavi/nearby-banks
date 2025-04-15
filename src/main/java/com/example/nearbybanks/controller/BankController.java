// package com.example.nearbybanks.controller;

// public class BankController {
    
// }
package com.example.nearbybanks.controller;

import com.example.nearbybanks.service.BankService;
import com.example.nearbybanks.geo.GeoService.BankLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/api/banks")
    public List<BankLocation> getNearbyBanks(@RequestParam String zipcode) throws Exception {
        return bankService.getNearbyBanks(zipcode);
    }
}