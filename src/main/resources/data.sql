INSERT INTO Drivers (name)
VALUES
    ('John'),
    ('James'),
    ('Jane'),
    ('David'),
    ('Billy');

INSERT INTO driver_auth(driver_id, accreditations)
VALUES
    ('1', 'FLAMMABLE');

INSERT INTO Tractors (name, power)
VALUES
    ('Red Renault', 'MEDIUM'),
    ('Green Iveco', 'LIGHT'),
    ('Black Volvo', 'HEAVY'),
    ('White Scania', 'MEDIUM'),
    ('Yellow Daf', 'MEDIUM'),
    ('Orange Scania', 'HEAVY');


INSERT INTO Cargo (description, weight)
VALUES
    ('Cargo 1', 'LIGHT'),
    ('Cargo 2', 'HEAVY');


INSERT INTO cargo_dangers (cargo_id, dangers)
VALUES
    (1, 'FLAMMABLE'),
    (2, 'RADIOACTIVE');


INSERT INTO cargo_compatible_trailers (cargo_id, compatible_trailers)
VALUES
    (1, 'GAS_TANK'),
    (2, 'FLAT');

INSERT INTO Trailer (name, type)
VALUES 
    ('Trailer 1', 'GAS_TANK'),
    ('Trailer 2', 'FLAT');
