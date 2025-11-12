package com.bo7.metaanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bo7.metaanalyzer.model.Loadout;

public interface LoadoutRepository extends JpaRepository<Loadout, Long> {
}
