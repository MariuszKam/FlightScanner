-- Add a new column to Artists table
ALTER TABLE Artists ADD COLUMN Website VARCHAR(255);

-- Rename a column in Events table
ALTER TABLE Events CHANGE COLUMN Date EventDate DATE;

-- Increase the capacity in Venues table
ALTER TABLE Venues MODIFY COLUMN Capacity INT UNSIGNED;

-- Add a foreign key to OrderItems table
ALTER TABLE OrderItems ADD CONSTRAINT fk_OrderItemTicket FOREIGN KEY (TicketID) REFERENCES Tickets(TicketID);

-- Drop a column from Customers table
ALTER TABLE Customers DROP COLUMN PhoneNumber;

-- Add a column to Staff table and foreign key

ALTER TABLE Staff ADD COLUMN  ArtistID INT; 
ALTER TABLE Staff ADD CONSTRAINT fk_ArtistStaff FOREIGN KEY (ArtistID) REFERENCES Artists(ArtistID);