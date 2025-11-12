-- Table: players
CREATE TABLE players (
    player_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    region VARCHAR(30)
);

-- Table: loadouts
CREATE TABLE loadouts (
    loadout_id SERIAL PRIMARY KEY,
    player_id INT REFERENCES players(player_id),
    primary_weapon VARCHAR(50),
    secondary_weapon VARCHAR(50),
    attachments TEXT[],
    perks TEXT[],
    map_name VARCHAR(50),
    mode VARCHAR(50)
);

-- Table: matches
CREATE TABLE matches (
    match_id SERIAL PRIMARY KEY,
    loadout_id INT REFERENCES loadouts(loadout_id),
    kills INT,
    deaths INT,
    accuracy DECIMAL(5,2),
    result VARCHAR(10),
    duration INT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
