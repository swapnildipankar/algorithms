package com.swapnildipankar.app;

import java.util.*;

/**
 * Created by sdipankar on 11/30/14.
 */
public class ClusterGenerator {
    private Vector<Point> vectorPoint;
    private Vector<Point> vectorCentroid;
    private Vector<Point> vectorCentroidFromClusters;
    private Map<Point, Vector<Point>> clusterMap;

    public ClusterGenerator(Vector<Point> vectorPoint, Vector<Point> vectorCentroid) {
        this.vectorPoint = vectorPoint;
        this.vectorCentroid = vectorCentroid;

        vectorCentroidFromClusters = new Vector<Point>(vectorCentroid.size());
        clusterMap = new TreeMap<Point, Vector<Point>>();
    }

    private double getEuclideanDistance(Point pointA, Point pointB) {
        double distanceSquared = Math.pow(pointA.getX() - pointB.getX(), 2) + Math.pow(pointA.getY() - pointB.getY(), 2);
        return Math.sqrt(distanceSquared);
    }

    private void generateClusterMap() {
        for(Point point : vectorPoint) {
            double minimumDistance = Double.MAX_VALUE;
            Point closestCentroid = new Point(0.0, 0.0);

            for(Point centroid : vectorCentroid) {
                double euclideanDistance = getEuclideanDistance(point, centroid);
                if(euclideanDistance < minimumDistance) {
                    minimumDistance = euclideanDistance;
                    closestCentroid = centroid;
                }
            }

            Vector<Point> currentMapValueVector = clusterMap.get(closestCentroid);
            if(currentMapValueVector == null) {
                currentMapValueVector = new Vector<Point>();
            }
            currentMapValueVector.add(point);
            clusterMap.put(closestCentroid, currentMapValueVector);
        }
    }

    public Map<Point, Vector<Point>> getClusterMap() {
        generateClusterMap();
        return clusterMap;
    }

    private void generateVectorCentroidFromClusters() {
        for(Map.Entry<Point, Vector<Point>> entry : clusterMap.entrySet()) {
            Vector<Point> pointVector = entry.getValue();
            double xTotal = 0.0;
            double yTotal = 0.0;
            for(Point point : pointVector) {
                xTotal += point.getX();
                yTotal += point.getY();
            }
            Point centroidFromCluster = new Point(xTotal/pointVector.size(), yTotal/pointVector.size());
            vectorCentroidFromClusters.add(centroidFromCluster);
        }
        Collections.sort(vectorCentroidFromClusters);
    }

    public Vector<Point> getVectorCentroidFromClusters() {
        generateVectorCentroidFromClusters();
        return vectorCentroidFromClusters;
    }

    public void printFormattedOutput() {
        int clusterCount = 1;
        for(Map.Entry<Point, Vector<Point>> entry : clusterMap.entrySet()) {
            System.out.println("-----------------------------------");
            System.out.println("Cluster " + clusterCount++);
            System.out.println("-----------------------------------");

            Point centroid = entry.getKey();
            System.out.println("Centroid => " + centroid.toString());

            Vector<Point> clusterPoints = entry.getValue();
            for(Point clusterPoint: clusterPoints) {
                System.out.println(clusterPoint.toString());
            }
        }
    }
}
