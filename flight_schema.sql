CREATE SCHEMA IF NOT EXISTS flightScanner;
USE flightScanner;

CREATE TABLE IF NOT EXISTS Airport(
id BIGINT PRIMARY KEY NOT NULL ,
name VARCHAR(255) ,
latitude DOUBLE ,
longitude DOUBLE 
);
select * from airport;

CREATE TABLE IF NOT EXISTS Airline(
id BIGINT PRIMARY KEY NOT NULL ,
name VARCHAR(255)
);
select * from airline;

CREATE TABLE IF NOT EXISTS Flight(
id BIGINT PRIMARY KEY NOT NULL,
name VARCHAR(255),
start_id BIGINT   ,
destination_id BIGINT ,
airline_id BIGINT,
price DOUBLE ,
FOREIGN KEY (start_id) REFERENCES Airport(id),
FOREIGN KEY (destination_id) REFERENCES Airport(id),
FOREIGN KEY (airline_id) REFERENCES Airline(id)
);
select * from flight;
show tables;