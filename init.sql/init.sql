CREATE TABLE requests(
    id_request serial PRIMARY KEY not null,
    number_one int,
    number_two int
);

CREATE TABLE responses(
    id_response serial PRIMARY KEY not null,
    id_request_fk int,
    endpoint  varchar(40),
    result_sum INT,
    CONSTRAINT fk_responses
    FOREIGN KEY (id_request_fk) REFERENCES requests (id_request)
);