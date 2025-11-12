package com.bo7.metaanalyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bo7.metaanalyzer.repository.MatchRepository;
import com.bo7.metaanalyzer.repository.LoadoutRepository;
import com.bo7.metaanalyzer.model.Match;
import com.bo7.metaanalyzer.model.Loadout;

import java.util.*;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service
public class AnalyticsService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private LoadoutRepository loadoutRepository;

    public Map<String, Object> getSummaryStats() {
        List<Match> matches = matchRepository.findAll();

        if (matches.isEmpty()) {
            return Map.of("message", "No matches recorded yet");
        }

        double avgKills = matches.stream().mapToInt(Match::getKills).average().orElse(0);
        double avgDeaths = matches.stream().mapToInt(Match::getDeaths).average().orElse(0);
        double avgKD = avgKills / (avgDeaths == 0 ? 1 : avgDeaths);
        double avgAccuracy = matches.stream()
                .map(Match::getAccuracy)
                .filter(Objects::nonNull)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0);

        long wins = matches.stream()
                .filter(m -> {
                    String r = m.getResult();
                    return r != null && r.equalsIgnoreCase("Win");
                })
                .count();
        double winRate = (double) wins / matches.size() * 100;

        Map<String, Object> result = new HashMap<>();
        result.put("averageKills", avgKills);
        result.put("averageDeaths", avgDeaths);
        result.put("KD_Ratio", avgKD);
        result.put("averageAccuracy", avgAccuracy);
        result.put("winRate", winRate);

        return result;
    }

    public List<Map<String, Object>> getTopWeaponsByKD() {
        List<Match> matches = matchRepository.findAll();
        List<Loadout> loadouts = loadoutRepository.findAll();

        Map<Long, Loadout> loadoutMap = loadouts.stream()
                .collect(Collectors.toMap(Loadout::getLoadout_id, l -> l));

        // Calculate KD ratio per weapon
        Map<String, List<Match>> matchesByWeapon = matches.stream()
                .collect(Collectors.groupingBy(m -> {
                    Loadout l = loadoutMap.get(m.getLoadout_id());
                    return l != null ? l.getPrimary_weapon() : "Unknown";
                }));

        List<Map<String, Object>> weaponStats = new ArrayList<>();

        for (String weapon : matchesByWeapon.keySet()) {
            List<Match> weaponMatches = matchesByWeapon.get(weapon);
            double avgKills = weaponMatches.stream().mapToInt(Match::getKills).average().orElse(0);
            double avgDeaths = weaponMatches.stream().mapToInt(Match::getDeaths).average().orElse(1);
            double kd = avgKills / (avgDeaths == 0 ? 1 : avgDeaths);

            Map<String, Object> stats = new HashMap<>();
            stats.put("weapon", weapon);
            stats.put("KD_Ratio", kd);
            weaponStats.add(stats);
        }

        return weaponStats.stream()
                .sorted(java.util.Comparator
                        .comparingDouble((Map<String, Object> m) -> ((Number) m.get("KD_Ratio")).doubleValue())
                        .reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getLoadoutBreakdown() {
    List<Match> matches = matchRepository.findAll();
    List<Loadout> loadouts = loadoutRepository.findAll();

    // Map loadout_id → loadout
    Map<Long, Loadout> loadoutMap = loadouts.stream()
        .collect(Collectors.toMap(Loadout::getLoadout_id, l -> l));

    // Group matches by loadout_id
    Map<Long, List<Match>> matchesByLoadout = matches.stream()
        .collect(Collectors.groupingBy(Match::getLoadout_id));

    List<Map<String, Object>> breakdown = new ArrayList<>();

    for (Long id : matchesByLoadout.keySet()) {
        Loadout l = loadoutMap.get(id);
        String weaponName = l != null ? l.getPrimary_weapon() : "Unknown";
        List<Match> mList = matchesByLoadout.get(id);

        double avgKills = mList.stream().mapToInt(Match::getKills).average().orElse(0);
        double avgDeaths = mList.stream().mapToInt(Match::getDeaths).average().orElse(1);
        double kd = avgKills / (avgDeaths == 0 ? 1 : avgDeaths);
        double accuracy = mList.stream()
                .map(Match::getAccuracy)
                .filter(Objects::nonNull)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0);
        long wins = mList.stream().filter(m -> {
            String r = m.getResult();
            return r != null && r.equalsIgnoreCase("Win");
        }).count();
        double winRate = (double) wins / mList.size() * 100;

        Map<String, Object> entry = new HashMap<>();
        entry.put("loadout_id", id);
        entry.put("weapon", weaponName);
        entry.put("KD_Ratio", kd);
        entry.put("Accuracy", accuracy);
        entry.put("WinRate", winRate);
        breakdown.add(entry);
    }

    return breakdown;
}

}
