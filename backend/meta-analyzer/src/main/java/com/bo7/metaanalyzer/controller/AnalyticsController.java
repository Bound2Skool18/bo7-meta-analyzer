package com.bo7.metaanalyzer.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.bo7.metaanalyzer.service.AnalyticsService;

import java.util.*;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        return analyticsService.getSummaryStats();
    }

    @GetMapping("/top-weapons")
    public List<Map<String, Object>> getTopWeapons() {
        return analyticsService.getTopWeaponsByKD();
    }
}
