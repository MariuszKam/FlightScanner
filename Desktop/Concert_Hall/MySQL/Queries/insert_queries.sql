-- Artists
INSERT INTO Artists (ArtistID, ArtistName, BirthDate, Country) VALUES (1, 'John Doe', '1990-01-15', 'USA');
INSERT INTO Artists (ArtistID, ArtistName, BirthDate, Country) VALUES (2, 'Jane Smith', '1985-05-20', 'UK');
INSERT INTO Artists (ArtistID, ArtistName, BirthDate, Country) VALUES (3, 'Kane Smith', '1986-05-20', 'TR');

-- Genres
INSERT INTO Genres (GenreID, GenreName) VALUES (1, 'Rock');
INSERT INTO Genres (GenreID, GenreName) VALUES (2, 'Pop');
INSERT INTO Genres (GenreID, GenreName) VALUES (3, 'Metal');

-- Venues
INSERT INTO Venues (VenueID, VenueName, Capacity, Location) VALUES (1, 'City Hall', 1000, 'Downtown');
INSERT INTO Venues (VenueID, VenueName, Capacity, Location) VALUES (2, 'Music Arena', 2000, 'Suburb');
INSERT INTO Venues (VenueID, VenueName, Capacity, Location) VALUES (3, 'Music Arena', 3000, 'Center');

-- Events
INSERT INTO Events (EventID, EventName, VenueID, Date, StartTime, EndTime) VALUES (1, 'RockFest', 1, '2023-07-15', '18:00:00', '23:00:00');
INSERT INTO Events (EventID, EventName, VenueID, Date, StartTime, EndTime) VALUES (2, 'PopConcert', 2, '2023-08-20', '19:30:00', '22:30:00');
INSERT INTO Events (EventID, EventName, VenueID, Date, StartTime, EndTime) VALUES (3, 'MetalFest', 3, '2023-08-20', '19:30:00', '22:30:00');

-- Tickets
INSERT INTO Tickets (TicketID, EventID, Price, TicketType) VALUES (1, 1, 50.00, 'Regular');
INSERT INTO Tickets (TicketID, EventID, Price, TicketType) VALUES (2, 2, 75.00, 'VIP');
INSERT INTO Tickets (TicketID, EventID, Price, TicketType) VALUES (3, 3, 75.00, 'VIP');

-- Customers
INSERT INTO Customers (CustomerID, FirstName, LastName, Email, PhoneNumber) VALUES (1, 'Alice', 'Johnson', 'alice@email.com', '1234567890');
INSERT INTO Customers (CustomerID, FirstName, LastName, Email, PhoneNumber) VALUES (2, 'Bob', 'Smith', 'bob@email.com', '9876543210');
INSERT INTO Customers (CustomerID, FirstName, LastName, Email, PhoneNumber) VALUES (3, 'Mehmet', 'Smith', 'Mehmet@email.com', '9876543210');


-- Orders
INSERT INTO Orders (OrderID, CustomerID, EventID, PurchaseDate, TotalAmount) VALUES (1, 1, 1, '2023-07-10', 50.00);
INSERT INTO Orders (OrderID, CustomerID, EventID, PurchaseDate, TotalAmount) VALUES (2, 2, 2, '2023-08-15', 75.00);
INSERT INTO Orders (OrderID, CustomerID, EventID, PurchaseDate, TotalAmount) VALUES (3, 3, 3, '2023-08-15', 75.00);

-- PaymentMethods
INSERT INTO PaymentMethods (PaymentMethodID, MethodName, Description) VALUES (1, 'Credit Card', 'Visa');
INSERT INTO PaymentMethods (PaymentMethodID, MethodName, Description) VALUES (2, 'PayPal', 'Online payment');
INSERT INTO PaymentMethods (PaymentMethodID, MethodName, Description) VALUES (3, 'Credit Card', 'Online payment');

-- OrderItems
INSERT INTO OrderItems (OrderItemID, OrderID, TicketID, Quantity, Subtotal) VALUES (1, 1, 1, 2, 100.00);
INSERT INTO OrderItems (OrderItemID, OrderID, TicketID, Quantity, Subtotal) VALUES (2, 2, 2, 1, 75.00);
INSERT INTO OrderItems (OrderItemID, OrderID, TicketID, Quantity, Subtotal) VALUES (3, 3, 3, 1, 75.00);

