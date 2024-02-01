package com.solvd.service.utility;

public class EarthDistanceCalculator {
    public static double calculateAngleBetweenCoordinates(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
        // convert from spherical coordinates to cartesian (with length = 1)
        double xA = 1.0 * Math.cos(degToRadians(longitudeA)) * Math.cos(degToRadians(latitudeA));
        double yA = 1.0 * Math.sin(degToRadians(longitudeA)) * Math.cos(degToRadians(latitudeA));
        double zA = 1.0 * Math.sin(degToRadians(latitudeA));
        double xB = 1.0 * Math.cos(degToRadians(longitudeB)) * Math.cos(degToRadians(latitudeB));
        double yB = 1.0 * Math.sin(degToRadians(longitudeB)) * Math.cos(degToRadians(latitudeB));
        double zB = 1.0 * Math.sin(degToRadians(latitudeB));

        // calculate dot product (which for two vectors will be equal to:
        // length of A * length of B * cos of angle between them,
        // since length of both vectors is 1, then dot product will be
        // cosines of angle between them
        double dotProduct = xA * xB + yA * yB + zA * zB;

        return radToDegrees(Math.acos(dotProduct));
    }

    public static double calculateDistanceBetweenCoordinates(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
        final double HALF_EARTH_CIRCUMFERENCE_KM = 20_015.017;

        /*
        calculates distance by checking what percentage of 180 degrees angle is the angle between
        two points on the globe, then multiplies that percentage by half of the circumference of earth
         */
        return (calculateAngleBetweenCoordinates(latitudeA, longitudeA, latitudeB, longitudeB) / 180)
                * HALF_EARTH_CIRCUMFERENCE_KM;
    }

    private static double degToRadians(double degrees) {
        return degrees * (Math.PI / 180);
    }

    private static double radToDegrees(double radians) {
        return radians * (180 / Math.PI);
    }
}
