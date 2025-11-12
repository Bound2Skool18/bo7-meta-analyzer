package com.bo7.metaanalyzer.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "loadouts")
public class Loadout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loadout_id;

    private Long player_id;
    private String primary_weapon;
    private String secondary_weapon;

    @ElementCollection
    private List<String> attachments;

    @ElementCollection
    private List<String> perks;

    private String map_name;
    private String mode;

    public Long getLoadout_id() {
        return loadout_id;
    }

    public void setLoadout_id(Long loadout_id) {
        this.loadout_id = loadout_id;
    }

    public Long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Long player_id) {
        this.player_id = player_id;
    }

    public String getPrimary_weapon() {
        return primary_weapon;
    }

    public void setPrimary_weapon(String primary_weapon) {
        this.primary_weapon = primary_weapon;
    }

    public String getSecondary_weapon() {
        return secondary_weapon;
    }

    public void setSecondary_weapon(String secondary_weapon) {
        this.secondary_weapon = secondary_weapon;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public List<String> getPerks() {
        return perks;
    }

    public void setPerks(List<String> perks) {
        this.perks = perks;
    }

    public String getMap_name() {
        return map_name;
    }

    public void setMap_name(String map_name) {
        this.map_name = map_name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}