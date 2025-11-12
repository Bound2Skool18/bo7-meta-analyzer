package com.bo7.metaanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bo7.metaanalyzer.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
}