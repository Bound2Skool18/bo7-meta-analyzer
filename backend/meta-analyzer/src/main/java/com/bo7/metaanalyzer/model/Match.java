package com.bo7.metaanalyzer.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long match_id;

    private Long loadout_id;
    private Integer kills;
    private Integer deaths;
    private BigDecimal accuracy;
    private String result;
    private Integer duration;
    

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    // Getters
    public Long getMatch_id() {
        return match_id;
    }

    public Long getLoadout_id() {
        return loadout_id;
    }

    public Integer getKills() {
        return kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public BigDecimal getAccuracy() {
        return accuracy;
    }

    public String getResult() {
        return result;
    }

    public Integer getDuration() {
        return duration;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setMatch_id(Long match_id) {
        this.match_id = match_id;
    }

    public void setLoadout_id(Long loadout_id) {
        this.loadout_id = loadout_id;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public void setAccuracy(BigDecimal accuracy) {
        this.accuracy = accuracy;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
