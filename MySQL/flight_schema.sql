CREATE SCHEMA IF NOT EXISTS flightScanner;
USE flightScanner;

CREATE TABLE IF NOT EXISTS airport(
id BIGINT PRIMARY KEY NOT NULL ,
name VARCHAR(255) ,
latitude DOUBLE ,
longitude DOUBLE 
);
select * from airport;

CREATE TABLE IF NOT EXISTS airline(
id BIGINT PRIMARY KEY NOT NULL ,
name VARCHAR(255)
);
select * from airline;

CREATE TABLE IF NOT EXISTS flight(
id BIGINT PRIMARY KEY NOT NULL,
name VARCHAR(255),
start_id BIGINT   ,
destination_id BIGINT ,
airline_id BIGINT,
price DOUBLE ,
FOREIGN KEY (start_id) REFERENCES airport(id),
FOREIGN KEY (destination_id) REFERENCES airport(id),
FOREIGN KEY (airline_id) REFERENCES airline(id)
);
select * from flight;
show tables;