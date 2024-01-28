use flightScanner;

insert into airlines
(id, name)
values
(1, 'Ryanair'),
(2, 'Qatar Airways'),
(3, 'Singapore Airlines'),
(4, 'Cathay Pacific Airways'),
(5, 'Lufthansa'),
(6, 'British Airways'),
(7, 'American Airlines'),
(8, 'Delta Air Lines'),
(9, 'LOT Polish Airlines'),
(10, 'Turkish Airlines');

insert into airports
(id, name, latitude, longitude)
values
(1, 'New York Airport', +40.7167, -74.0000),
(2, 'Lisbona Airport', +38.7072,  -9.1355),
(3, 'Madrid Airport', +40.4167, -3.7033),
(4, 'London Airport', +51.5002, -0.1262),
(5, 'Paris Airport', +48.8567, +2.3510),
(6, 'Amsterdam Airport', +52.3738, +4.8910),
(7, 'Frankfurt Airport', +50.116667, +8.683333),
(8, 'Instambul Airport', +41, +29),
(9, 'Dubai Airport', +25.266667, +55.333333),
(10, 'Tokyo Airport', +35.6785, +139.6823),
(11, 'Sao Paulo Airport', -23.5,  -46.616667),
(12, 'Sydney Airport', -33.866667, +151.2);

insert into flights
(id, name, start_id, destination_id, airline_id, price)
values
(1, 'FL001', 1, 11, 1, 410),
(2, 'FL002', 1, 11, 2, 511),
(3, 'FL003', 11, 1, 3, 368),
(4, 'FL004', 1, 4, 4, 1121),
(5, 'FL005', 1, 2, 5, 721),
(6, 'FL006', 2, 1, 6, 801),
(7, 'FL007', 4, 3, 7, 121),
(8, 'FL008', 4, 3, 8, 310),
(9, 'FL009', 3, 4, 9, 220),
(10, 'FL010', 3, 2, 10, 82),
(11, 'FL011', 2, 3, 1, 210),
(12, 'FL012', 2, 3, 2, 100),
(13, 'FL013', 3, 5, 3, 170),
(14, 'FL014', 5, 4, 4, 104),
(15, 'FL015', 3, 8, 5, 1023),
(16, 'FL016', 8, 7, 6, 481),
(17, 'FL017', 7, 8, 7, 424),
(18, 'FL018', 5, 7, 8, 81),
(19, 'FL019', 5, 7, 9, 110),
(20, 'FL020', 7, 6, 10, 98),
(21, 'FL021', 6, 7, 1, 91),
(22, 'FL022', 6, 5, 2, 72),
(23, 'FL023', 4, 6, 3, 41),
(24, 'FL024', 4, 7, 4, 61),
(25, 'FL025', 4, 10, 5, 2210),
(26, 'FL026', 10, 1, 6, 1010),
(27, 'FL027', 8, 9, 7, 1410),
(28, 'FL028', 8, 9, 8, 675),
(29, 'FL029', 9, 3, 9, 1300),
(30, 'FL030', 9, 12, 10, 3270),
(31, 'FL031', 9, 12, 1, 1870),
(32, 'FL032', 12, 9, 2, 2017),
(33, 'FL033', 12, 10, 3, 980),
(34, 'FL034', 10, 12, 4, 899),
(35, 'FL035', 10, 12, 5, 1370),
(36, 'FL036', 9, 10, 6, 1981),
(37, 'FL037', 3, 11, 7, 1702),
(38, 'FL038', 11, 2, 8, 1510),
(39, 'FL039', 11, 12, 9, 891);
