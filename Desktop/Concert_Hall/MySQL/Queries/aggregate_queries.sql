-- Count the number of events for each venue
SELECT Venues.VenueID, VenueName, COUNT(Events.EventID) AS EventCount
FROM Venues
LEFT JOIN Events ON Venues.VenueID = Events.VenueID
GROUP BY Venues.VenueID;

-- Calculate the total revenue for each event
SELECT Events.EventID, EventName, SUM(OrderItems.Subtotal) AS TotalRevenue
FROM Events
LEFT JOIN Orders ON Events.EventID = Orders.EventID
LEFT JOIN OrderItems ON Orders.OrderID = OrderItems.OrderID
GROUP BY Events.EventID;

-- Find the average ticket price for each genre
SELECT Genres.GenreID, GenreName, AVG(Tickets.Price) AS AvgTicketPrice
FROM Genres
LEFT JOIN Artists ON Genres.GenreID = Artists.GenreID
LEFT JOIN Events ON Artists.ArtistID = Events.ArtistID
LEFT JOIN Tickets ON Events.EventID = Tickets.EventID
GROUP BY Genres.GenreID;

-- Find the maximum capacity among venues
SELECT MAX(Capacity) AS MaxCapacity
FROM Venues;

-- Calculate the total number of tickets sold for each event
SELECT Events.EventID, EventName, SUM(OrderItems.Quantity) AS TotalTicketsSold
FROM Events
LEFT JOIN Orders ON Events.EventID = Orders.EventID
LEFT JOIN OrderItems ON Orders.OrderID = OrderItems.OrderID
GROUP BY Events.EventID;

-- Find the artist with the highest total revenue
SELECT Artists.ArtistID, ArtistName, SUM(OrderItems.Subtotal) AS TotalRevenue
FROM Artists
LEFT JOIN Events ON Artists.ArtistID = Events.ArtistID
LEFT JOIN Tickets ON Events.EventID = Tickets.EventID
LEFT JOIN Orders ON Tickets.TicketID = OrderItems.TicketID
GROUP BY Artists.ArtistID
ORDER BY TotalRevenue DESC
LIMIT 1;
