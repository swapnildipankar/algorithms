package com.swapnildipankar.app;

import java.util.Random;
import java.util.Vector;

/**
 * Created by sdipankar on 11/30/14.
 */
public class RandomPointsGenerator {
    private int numberOfPoints;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private Vector<Point> pointVector;

    public RandomPointsGenerator(int numberOfPoints, double minX, double maxX, double minY, double maxY) {
        this.numberOfPoints = numberOfPoints;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

        pointVector = new Vector<Point>(numberOfPoints);
    }

    public Vector<Point> getRandomPoints() {
        for(int index = 0; index < numberOfPoints; ++index) {
            Point point = new Point(getRandomValueInRange(minX, maxX), getRandomValueInRange(minY, maxY));
            pointVector.add(point);
        }

        return pointVector;
    }

    private double getRandomValueInRange(double min, double max) {
        double range = max - min + 1.0;
        Random random = new Random();

//        return random.nextDouble() * range;
        return random.nextGaussian() * range;
    }

    public void printFormattedOutput() {
        System.out.println("['X', 'Y'],");
        for(Point point : pointVector) {
            System.out.println(point.toString() + ",");
        }
    }
}
