SELECT * FROM Artists
JOIN Genres ON Artists.GenreID = Genres.GenreID
JOIN Events ON Events.EventID = Events.EventID
JOIN Venues ON Events.VenueID = Venues.VenueID
JOIN Tickets ON Tickets.EventID = Events.EventID
JOIN Customers ON Customers.CustomerID = Orders.CustomerID
JOIN Orders ON Orders.EventID = Events.EventID
JOIN PaymentMethods ON PaymentMethods.PaymentMethodID = PaymentMethods.PaymentMethodID
JOIN OrderItems ON OrderItems.OrderID = Orders.OrderID
JOIN StaffRoles ON StaffRoles.StaffID = StaffRoles.StaffID
JOIN Staff ON Staff.StaffID = StaffRoles.StaffID
JOIN Roles ON Roles.RoleID = StaffRoles.RoleID;
