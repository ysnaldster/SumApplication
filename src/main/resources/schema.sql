DROP TABLE IF EXISTS requests;
CREATE TABLE requests(
id_request serial PRIMARY KEY not null,
    number_one int,
    number_two int
);

DROP TABLE IF EXISTS responses;
CREATE TABLE responses(
    id_response serial PRIMARY KEY not null,
    id_request_fk serial,
    endpoint  varchar(40),
    result_sum INT,
    FOREIGN KEY (id_request_fk) REFERENCES requests (id_request)
);