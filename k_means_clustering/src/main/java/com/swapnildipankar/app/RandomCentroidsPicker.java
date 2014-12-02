package com.swapnildipankar.app;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * Created by sdipankar on 11/30/14.
 */
public class RandomCentroidsPicker {
    private int numberOfCentroids;
    private Vector<Point> pointVector;
    private Vector<Point> centroidVector;

    public RandomCentroidsPicker(int numberOfCentroids, Vector<Point> pointVector) {
        this.numberOfCentroids = numberOfCentroids;
        this.pointVector = pointVector;
        centroidVector = new Vector<Point>(numberOfCentroids);
    }

    private void generateCentroidVector() {
        for(int index = 0; index < numberOfCentroids; ++index) {
            int randomIndex = getRandomInteger(pointVector.size());
            Point point = pointVector.elementAt(randomIndex);
            pointVector.remove(randomIndex);
            centroidVector.add(point);
        }
        Collections.sort(centroidVector);
    }

    public Vector<Point> getCentroidVector() {
        generateCentroidVector();
        return centroidVector;
    }

    private int getRandomInteger(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    @Override
    public String toString() {
        return "RandomCentroidsPicker{" +
                "numberOfCentroids=" + numberOfCentroids +
                ", centroidVector=" + centroidVector +
                '}';
    }
}
