package com.bo7.metaanalyzer.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.bo7.metaanalyzer.model.Match;
import com.bo7.metaanalyzer.repository.MatchRepository;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = "*")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        return matchRepository.save(match);
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
}