CREATE SCHEMA IF NOT EXISTS flightScanner;
USE flightScanner;

CREATE TABLE IF NOT EXISTS Airport(
airport_id INT PRIMARY KEY NOT NULL ,
airport_name VARCHAR(255) ,
x_latitude DOUBLE ,
y_longitude DOUBLE 
);
select * from airport;

CREATE TABLE IF NOT EXISTS Airline(
airline_id INT PRIMARY KEY NOT NULL ,
airline_name VARCHAR(255)
);
select * from airline;

CREATE TABLE IF NOT EXISTS Flight(
flight_id INT PRIMARY KEY NOT NULL,
flight_name VARCHAR(255),
flight_airline_id INT,
flight_price DOUBLE ,
start_airport_id INT   ,
destination_airport_id INT ,
FOREIGN KEY (start_airport_id) REFERENCES Airport(airport_id),
FOREIGN KEY (destination_airport_id) REFERENCES Airport(airport_id),
FOREIGN KEY (flight_airline_id) REFERENCES Airline(airline_id)
);
select * from flight;