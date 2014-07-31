DELETE activity;

CREATE TABLE IF NOT EXISTS activity (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, strava_id INT NOT NULL, external_id varchar(255), name varchar(255));

CREATE INDEX IF NOT EXISTS idx_activity_name ON activity(name);