package com.bo7.metaanalyzer.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.bo7.metaanalyzer.model.Loadout;
import com.bo7.metaanalyzer.repository.LoadoutRepository;
import java.util.List;

@RestController
@RequestMapping("/api/loadouts")
@CrossOrigin(origins = "*")
public class LoadoutController {

    @Autowired
    private LoadoutRepository loadoutRepository;

    // Create new loadout
    @PostMapping
    public Loadout createLoadout(@RequestBody Loadout loadout) {
        return loadoutRepository.save(loadout);
    }

    // Get all loadouts
    @GetMapping
    public List<Loadout> getAllLoadouts() {
        return loadoutRepository.findAll();
    }
}
