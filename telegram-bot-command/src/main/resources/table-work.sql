CREATE TABLE animal
(
    id              BIGINT PRIMARY KEY,
    name            TEXT,
    kind_of_animal  TEXT,
    age             BIGINT,
    invalid         BOOLEAN,
    person_id       BIGINT REFERENCES person(id)
);


CREATE TABLE person
(
    id           BIGINT PRIMARY KEY,
    username     TEXT,
    phone_number TEXT,
    contact_name TEXT,
    chat_id      BIGINT,
    is_adoptive  BOOLEAN,
    start_probation_date  DATE,
    end_probation_date   DATE
);


CREATE TABLE volunteer
(
    id           BIGINT PRIMARY KEY,
    username     TEXT,
    phone_number TEXT,
    contact_name TEXT
);


CREATE TABLE report(
    id              BIGINT PRIMARY KEY,
    username        TEXT,
    message         TEXT,
    photo           OID,
    date_report     DATE
);
