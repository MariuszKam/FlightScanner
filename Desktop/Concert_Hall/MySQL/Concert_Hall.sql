-- -----------------------------------------------------
-- Schema concert_hall
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS Concert_Hall DEFAULT CHARACTER SET utf8 ;
USE `concert_hall` ;

-- -----------------------------------------------------
-- Schema artists
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.artists (
	ArtistID INT PRIMARY KEY,
    ArtistName VARCHAR(255) NOT NULL,
    BirthDate DATE,
    Country VARCHAR(255),
    GenreID INT,
    CONSTRAINT fk_Genre FOREIGN KEY (GenreID) REFERENCES Genres(GenreID)
);
-- -----------------------------------------------------
-- Schema genres
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.genres (
	GenreID INT PRIMARY KEY,
    GenreName VARCHAR(255) NOT NULL
);

-- -----------------------------------------------------
-- Schema Events
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.Events (
	EventID INT PRIMARY KEY,
    EventName VARCHAR(255) NOT NULL,
    Date DATE,
    StartTime TIME,
    EndTime TIME,  
    venueID INT, 
    CONSTRAINT fk_venue FOREIGN KEY (venueID) REFERENCES Venue (venueID)
        
        
);
-- -----------------------------------------------------
-- Schema Venues
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.Venues (
	VenueID INT PRIMARY KEY NOT NULL ,
    VenueName VARCHAR(255) NOT NULL,
    Capacity INT,
    Location VARCHAR(255)
);
-- -----------------------------------------------------
-- Schema Tickets
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.Tickets (
	TicketID INT PRIMARY KEY,
    EventID INT,
    Price DECIMAL (10,2) NOT NULL,
    TicketType VARCHAR(50),
    CONSTRAINT fk_Event FOREIGN KEY (EventID) REFERENCES Events(EventID)
);
-- -----------------------------------------------------
-- Schema Customers
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.Customers (
	CustomerID INT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(15)
);
-- -----------------------------------------------------
-- Schema Orders
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.Orders (
	OrderID INT PRIMARY KEY,
    CustomerID INT, 
    EventID INT, 
    PurchaseDate DATE,
    TotalAmount DECIMAL(10, 2),
    CONSTRAINT fk_CustomerOrder FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    CONSTRAINT fk_EventOrder FOREIGN KEY (EventID) REFERENCES Events(EventID)
);
-- -----------------------------------------------------
-- Schema PaymentMethods
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.PaymentMethods (
	PaymentMethodID INT PRIMARY KEY,
    MethodName VARCHAR(50) NOT NULL,
    Description VARCHAR(255)
);
-- -----------------------------------------------------
-- Schema PaymentMethods
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.OrderItems (
	OrderItemID INT PRIMARY KEY,
    OrderID INT, 
    TicketID INT,
    Quantity INT,
    Subtotal DECIMAL(10, 2),
    CONSTRAINT fk_Order FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    CONSTRAINT fk_TicketOrderItem FOREIGN KEY (TicketID) REFERENCES Tickets(TicketID)
);
-- -----------------------------------------------------
-- Schema Staff
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.Staff (
	StaffID INT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Position VARCHAR(50)
);
-- -----------------------------------------------------
-- Schema Roles
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.Roles (
    RoleID INT PRIMARY KEY,
    RoleName VARCHAR(50) NOT NULL,
    Description VARCHAR(255)
);
-- -----------------------------------------------------
-- Schema StaffRoles
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Concert_Hall.StaffRoles (
    StaffRoleID INT PRIMARY KEY,
    StaffID INT, 
    RoleID INT, 
    CONSTRAINT fk_StaffStaffRole FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
    CONSTRAINT fk_RoleStaffRole FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);


SELECT * FROM  Concert_Hall.staff;
