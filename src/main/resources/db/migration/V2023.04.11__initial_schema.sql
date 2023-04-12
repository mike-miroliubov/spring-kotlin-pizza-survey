CREATE TABLE survey.respondent (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE survey.topping_choice (
  id BIGSERIAL NOT NULL PRIMARY KEY,
  respondent_id BIGINT NOT NULL REFERENCES respondent(id),
  name VARCHAR(100) NOT NULL
);

CREATE INDEX topping_choice_name ON survey.topping_choice(name);