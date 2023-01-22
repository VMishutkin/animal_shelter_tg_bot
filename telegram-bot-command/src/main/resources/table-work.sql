CREATE TABLE person
(
    id           BIGSERIAL PRIMARY KEY,
    username     TEXT,
    phone_number TEXT,
    contact_name TEXT,
    chat_id      BIGINT,
    is_adoptive  BOOLEAN,
    start_probation_date  DATE,
    end_probation_date   DATE
);


CREATE TABLE animal
(
    id              BIGSERIAL PRIMARY KEY,
    name            TEXT,
    kind_of_animal  TEXT,
    age             BIGINT,
    invalid         BOOLEAN,
    person_id       BIGINT REFERENCES person(id)
);


CREATE TABLE volunteer
(
    id           BIGSERIAL PRIMARY KEY,
    username     TEXT,
    phone_number TEXT,
    contact_name TEXT
);


CREATE TABLE report(
    id              BIGSERIAL PRIMARY KEY,
    username        TEXT,
    message         TEXT,
    photo           OID,
    date_report     DATE
);
