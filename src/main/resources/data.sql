CREATE TABLE player_profile (
    player_profile_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    credential VARCHAR(255),
    created TIMESTAMP,
    modified TIMESTAMP,
    last_session TIMESTAMP,
    total_spent BIGINT,
    total_refund BIGINT,
    total_transactions BIGINT,
    last_purchase TIMESTAMP,
    level BIGINT,
    xp BIGINT,
    total_play_time BIGINT,
    country VARCHAR(255),
    language VARCHAR(255),
    birth_date TIMESTAMP,
    gender VARCHAR(255),
    custom_field VARCHAR(255)
);

-- Create Campaign table
CREATE TABLE campaign (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game VARCHAR(255),
    name VARCHAR(255),
    priority DOUBLE,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    enabled BOOLEAN,
    last_updated TIMESTAMP
);

-- Create Level table (for matcher)
CREATE TABLE level (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    min INT,
    max INT
);

-- Create Has table (for matcher)
CREATE TABLE has (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(255),
    items VARCHAR(255)
);

-- Create DoesNotHave table (for matcher)
CREATE TABLE does_not_have (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    items VARCHAR(255)
);

-- Create Matcher table
CREATE TABLE matcher (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    level_id BIGINT,
    has_id BIGINT,
    does_not_have_id BIGINT,
    FOREIGN KEY (level_id) REFERENCES level(id),
    FOREIGN KEY (has_id) REFERENCES has(id),
    FOREIGN KEY (does_not_have_id) REFERENCES does_not_have(id)
);

-- Create Device table
CREATE TABLE device (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255),
    carrier VARCHAR(255),
    firmware VARCHAR(255),
    player_profile_id BIGINT,
    FOREIGN KEY (player_profile_id) REFERENCES player_profile(player_profile_id)
);

-- Create Inventory table
CREATE TABLE inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    player_profile_id BIGINT,
    cash BIGINT,
    coins BIGINT,
    FOREIGN KEY (player_profile_id) REFERENCES player_profile(player_profile_id)
);

-- Create Inventory Items table
CREATE TABLE inventory_items (
    inventory_id BIGINT,
    item_key VARCHAR(255),
    item_value INT,
    FOREIGN KEY (inventory_id) REFERENCES inventory(id)
);

-- Create Clan table
CREATE TABLE clan (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    player_profile_id BIGINT,
    name VARCHAR(255),
    FOREIGN KEY (player_profile_id) REFERENCES player_profile(player_profile_id)
);

-- Create Campaign Matcher table (assuming a join table is necessary)
CREATE TABLE campaign_matcher (
    campaign_id BIGINT,
    matcher_id BIGINT,
    FOREIGN KEY (campaign_id) REFERENCES campaign(id),
    FOREIGN KEY (matcher_id) REFERENCES matcher(id)
);

-- Create PlayerProfile Campaigns table (assuming a join table for active campaigns)
CREATE TABLE player_profile_campaigns (
    player_profile_id BIGINT,
    campaign_id BIGINT,
    FOREIGN KEY (player_profile_id) REFERENCES player_profile(player_profile_id),
    FOREIGN KEY (campaign_id) REFERENCES campaign(id)
);

-- Create PlayerProfile Clan table (assuming clan is part of player profile)
CREATE TABLE player_profile_clan (
    player_profile_id BIGINT,
    clan_id BIGINT,
    FOREIGN KEY (player_profile_id) REFERENCES player_profile(player_profile_id),
    FOREIGN KEY (clan_id) REFERENCES clan(id)
);

-- Insert into Campaign table
INSERT INTO campaign (id, game, name, priority, start_date, end_date, enabled, last_updated) VALUES
(1, 'mygame', 'mycampaign', 10.5, '2022-01-25 00:00:00', '2022-02-25 00:00:00', true, '2021-07-13 11:46:58');

-- Insert into Level table (for matcher)
INSERT INTO level (id, min, max) VALUES
(1, 1, 3);

-- Insert into Has table (for matcher)
INSERT INTO has (id, country, items) VALUES
(1, 'US,RO,CA', 'item_1');

-- Insert into DoesNotHave table (for matcher)
INSERT INTO does_not_have (id, items) VALUES
(1, 'item_4');

-- Insert into Matcher table
INSERT INTO matcher (id, level_id, has_id, does_not_have_id) VALUES
(1, 1, 1, 1);

-- Insert into PlayerProfile table
INSERT INTO player_profile (player_profile_id, credential, created, modified, last_session, total_spent, total_refund, total_transactions, last_purchase, level, xp, total_play_time, country, language, birth_date, gender, custom_field) VALUES
(1, 'apple_credential', '2021-01-10 13:37:17', '2021-01-23 13:37:17', '2021-01-23 13:37:17', 400, 0, 5, '2021-01-22 13:37:17', 3, 1000, 144, 'CA', 'fr', '2000-01-10 13:37:17', 'male', 'mycustom');

-- Insert into Device table
INSERT INTO device (id, model, carrier, firmware, player_profile_id) VALUES
(1, 'apple iphone 11', 'vodafone', '123', 1);

-- Insert into Inventory table
INSERT INTO inventory (id, player_profile_id, cash, coins) VALUES
(1, 1, 123, 123);

-- Insert into inventory items table
INSERT INTO inventory_items (inventory_id, item_key, item_value) VALUES
(1, 'item_1', 1),
(1, 'item_34', 3),
(1, 'item_55', 2);

-- Insert into Clan table
INSERT INTO clan (id, player_profile_id, name) VALUES
(1, 1, 'Hello world clan');

