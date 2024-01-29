package com.solvd.unintegrated.pathfinder;

import com.solvd.unintegrated.pathfinder.PathfindingService;
import com.solvd.unintegrated.pathfinder.dummy.DummyAirport;
import com.solvd.unintegrated.pathfinder.dummy.DummyFlight;
import com.solvd.unintegrated.pathfinder.impl.PathfindingServiceImpl;
import com.solvd.unintegrated.pathfinder.util.EarthDistanceCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PathfinderDemonstration {
    public static void main(String[] args) {
        PathfindingService pathfindingService = new PathfindingServiceImpl();
        List<DummyAirport> airportList = generateConnections();

        System.out.println("Select start and destination:");
        Scanner scanner = new Scanner(System.in);
        for( int i = 0; i< airportList.size(); i++ ) {
           System.out.printf("%2d - %s%n", i, airportList.get(i).getName());
        }
        System.out.println("Start airport no.: ");
        DummyAirport start = airportList.get(scanner.nextInt());

        System.out.println("Destination airport no.: ");
        DummyAirport destination = airportList.get(scanner.nextInt());
        System.out.printf("Selected %s as start%n", start.getName());
        System.out.printf("Selected %s as destination%n", start.getName());


        System.out.println("Shortest path:");
        List<DummyFlight> path = pathfindingService.findShortestPath(start, destination).get();
        for( DummyFlight flight : path) {
            System.out.printf("%s -> %s (price: %.2f)\n",
                    flight.getStart().getName(),
                    flight.getDestination().getName(),
                    flight.getPrice());
        }

        System.out.println("Cheapest path:");
        path = pathfindingService.findCheapestPath(start, destination).get();
        for( DummyFlight flight : path) {
            System.out.printf("%s -> %s (price: %.2f)\n",
                    flight.getStart().getName(),
                    flight.getDestination().getName(),
                    flight.getPrice());
        }
    }

    private static List<DummyAirport> generateConnections() {
        DummyAirport a1 = new DummyAirport("New York Airport", 40.7167, -74);
        DummyAirport a2 = new DummyAirport("Lisbona Airport", 38.7072, -9.1355);
        DummyAirport a3 = new DummyAirport("Madrid Airport", 40.4167, -3.7033);
        DummyAirport a4 = new DummyAirport("London Airport", 51.5002, -0.1262);
        DummyAirport a5 = new DummyAirport("Paris Airport", 48.8567, 2.351);
        DummyAirport a6 = new DummyAirport("Amsterdam Airport", 52.3738, 4.891);
        DummyAirport a7 = new DummyAirport("Frankfurt Airport", 50.116667, 8.683333);
        DummyAirport a8 = new DummyAirport("Instambul Airport", 41, 29);
        DummyAirport a9 = new DummyAirport("Dubai Airport", 25.266667, 55.333333);
        DummyAirport a10 = new DummyAirport("Tokyo Airport", 35.6785, 139.6823);
        DummyAirport a11 = new DummyAirport("Sao Paulo Airport", -23.5, -46.616667);
        DummyAirport a12 = new DummyAirport("Sydney Airport", -33.866667, 151.2);
        List<DummyAirport> airportList = List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);

        DummyFlight f1 = new DummyFlight("FL001", 410, a11);
        a1.addDestination(f1);
        DummyFlight f2 = new DummyFlight("FL002", 511, a11);
        a1.addDestination(f2);
        DummyFlight f3 = new DummyFlight("FL003", 368, a1);
        a11.addDestination(f3);
        DummyFlight f4 = new DummyFlight("FL004", 1121, a4);
        a1.addDestination(f4);
        DummyFlight f5 = new DummyFlight("FL005", 721, a2);
        a1.addDestination(f5);
        DummyFlight f6 = new DummyFlight("FL006", 801, a1);
        a2.addDestination(f6);
        DummyFlight f7 = new DummyFlight("FL007", 121, a3);
        a4.addDestination(f7);
        DummyFlight f8 = new DummyFlight("FL008", 310, a3);
        a4.addDestination(f8);
        DummyFlight f9 = new DummyFlight("FL009", 220, a4);
        a3.addDestination(f9);
        DummyFlight f10 = new DummyFlight("FL010", 82, a2);
        a3.addDestination(f10);
        DummyFlight f11 = new DummyFlight("FL011", 210, a3);
        a2.addDestination(f11);
        DummyFlight f12 = new DummyFlight("FL012", 100, a3);
        a2.addDestination(f12);
        DummyFlight f13 = new DummyFlight("FL013", 170, a5);
        a3.addDestination(f13);
        DummyFlight f14 = new DummyFlight("FL014", 104, a4);
        a5.addDestination(f14);
        DummyFlight f15 = new DummyFlight("FL015", 1023, a8);
        a3.addDestination(f15);
        DummyFlight f16 = new DummyFlight("FL016", 481, a7);
        a8.addDestination(f16);
        DummyFlight f17 = new DummyFlight("FL017", 424, a8);
        a7.addDestination(f17);
        DummyFlight f18 = new DummyFlight("FL018", 81, a7);
        a5.addDestination(f18);
        DummyFlight f19 = new DummyFlight("FL019", 110, a7);
        a5.addDestination(f19);
        DummyFlight f20 = new DummyFlight("FL020", 98, a6);
        a7.addDestination(f20);
        DummyFlight f21 = new DummyFlight("FL021", 91, a7);
        a6.addDestination(f21);
        DummyFlight f22 = new DummyFlight("FL022", 72, a5);
        a6.addDestination(f22);
        DummyFlight f23 = new DummyFlight("FL023", 41, a6);
        a4.addDestination(f23);
        DummyFlight f24 = new DummyFlight("FL024", 61, a7);
        a4.addDestination(f24);
        DummyFlight f25 = new DummyFlight("FL025", 2210, a10);
        a4.addDestination(f25);
        DummyFlight f26 = new DummyFlight("FL026", 1010, a1);
        a10.addDestination(f26);
        DummyFlight f27 = new DummyFlight("FL027", 1410, a9);
        a8.addDestination(f27);
        DummyFlight f28 = new DummyFlight("FL028", 675, a9);
        a8.addDestination(f28);
        DummyFlight f29 = new DummyFlight("FL029", 1300, a3);
        a9.addDestination(f29);
        DummyFlight f30 = new DummyFlight("FL030", 3270, a12);
        a9.addDestination(f30);
        DummyFlight f31 = new DummyFlight("FL031", 1870, a12);
        a9.addDestination(f31);
        DummyFlight f32 = new DummyFlight("FL032", 2017, a9);
        a12.addDestination(f32);
        DummyFlight f33 = new DummyFlight("FL033", 980, a10);
        a12.addDestination(f33);
        DummyFlight f34 = new DummyFlight("FL034", 899, a12);
        a10.addDestination(f34);
        DummyFlight f35 = new DummyFlight("FL035", 1370, a12);
        a10.addDestination(f35);
        DummyFlight f36 = new DummyFlight("FL036", 1981, a10);
        a9.addDestination(f36);
        DummyFlight f37 = new DummyFlight("FL037", 1702, a11);
        a3.addDestination(f37);
        DummyFlight f38 = new DummyFlight("FL038", 1510, a2);
        a11.addDestination(f38);
        DummyFlight f39 = new DummyFlight("FL039", 891, a12);
        a11.addDestination(f39);
        return airportList;
    }
}
