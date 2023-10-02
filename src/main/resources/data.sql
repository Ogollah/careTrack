-- Define the "locations" table
CREATE TABLE IF NOT EXISTS locations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    longitude VARCHAR(20) NOT NULL,
    latitude VARCHAR(20) NOT NULL,
    mfl VARCHAR(10) NOT NULL
);

-- Define the "persons" table (common attributes for User, Patient, and Person)
CREATE TABLE IF NOT EXISTS persons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    sex VARCHAR(10),
    birth_date DATE,
    phone VARCHAR(20),
    IDNumber INTEGER,
    registeredBy_id BIGINT, -- Reference to the person who registered this person
    FOREIGN KEY (registeredBy_id) REFERENCES persons(id)
);

-- Define the "roles" table
CREATE TABLE IF NOT EXISTS roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20)
);

-- Define the "users" table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255),
    enabled BOOLEAN,
    location_id BIGINT, -- Reference to the user's location
    FOREIGN KEY (location_id) REFERENCES locations(id)
);

-- Define the "users_roles" table (for Many-to-Many relationship)
CREATE TABLE IF NOT EXISTS users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Define the "patients" table
CREATE TABLE IF NOT EXISTS patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_number VARCHAR(20) NOT NULL
);

-- Define the "visits" table
CREATE TABLE visits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    visit_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    height DOUBLE NOT NULL,
    weight DOUBLE NOT NULL,
    bmi DOUBLE NOT NULL,
    general_health VARCHAR(255),
    on_diet TINYINT(1) ,
    taking_drugs TINYINT(1),
    comments TEXT,
    status VARCHAR(255),
    patient_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert dummy data
-- Locations
INSERT INTO locations (name, longitude, latitude, mfl) VALUES
('Hospital A', '12.3456', '34.5678', 'MFL001'),
('Clinic B', '23.4567', '45.6789', 'MFL002');

-- Roles
INSERT INTO roles (name) VALUES
('ADMIN'),
('PROVIDER');

-- Persons
INSERT INTO persons (first_name, last_name, sex, birth_date, phone, IDNumber, registeredBy_id) VALUES
('John', 'Doe', 'Male', '1990-01-01', '1234567890', 123456, NULL),
('Alice', 'Smith', 'Female', '1988-03-15', '9876543210', 654321, NULL);

-- Users
INSERT INTO users (username, password, enabled, location_id) VALUES
('admin', '$2a$10$kWWOnNOiToOxcIQ7UJ.cB.XFAflYvMS5BPASR1eqqojc6H9ELWUfC', true, 1), -- admin Password @zhwaniPass
('user1', '$2a$10$kWWOnNOiToOxcIQ7UJ.cB.XFAflYvMS5BPASR1eqqojc6H9ELWUfC', true, 2); -- user1 Password @zhwaniPass

-- Users Roles (Many-to-Many)
INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1), -- admin has ROLE_ADMIN
(2, 2); -- user1 has ROLE_PROVIDER;
