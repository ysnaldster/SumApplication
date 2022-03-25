DROP TABLE IF EXISTS requests;
CREATE TABLE requests(
id_request INT AUTO_INCREMENT PRIMARY KEY,
    number_one int,
    number_two int
);

DROP TABLE IF EXISTS responses;
CREATE TABLE responses(
    id_response INT AUTO_INCREMENT PRIMARY KEY,
    id_request_fk INT AUTO_INCREMENT,
    endpoint  varchar(40),
    result_sum INT,
    FOREIGN KEY (id_request_fk) REFERENCES requests (id_request)
);