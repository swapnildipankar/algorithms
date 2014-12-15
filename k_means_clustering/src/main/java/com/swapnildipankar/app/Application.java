package com.swapnildipankar.app;

import com.swapnildipankar.datagenerator.DataGenerator;
import com.swapnildipankar.datagenerator.RandomPointsGenerator;
import com.swapnildipankar.datagenerator.RealPointsGenerator;

import java.util.Map;
import java.util.Vector;

public class Application {
    public static void main(String[] args) {
        boolean plotEveryIteration = true;

        int numberOfPoints = 10000;
        int numberOfCentroids = 10;

        double minX = 0.0;
        double maxX = 100.0;
        double minY = 0.0;
        double maxY = 100.0;

        DataGenerator dataGenerator = new RandomPointsGenerator(numberOfPoints, minX, maxX, minY, maxY);
//        DataGenerator dataGenerator = new RealPointsGenerator("src/main/resources/input_data_germany_cities.txt");
        Vector<Point> pointVector = dataGenerator.getDataPoints();
//        System.out.println("-----------------------------------");
//        System.out.println("Random Points");
//        System.out.println("-----------------------------------");
//        randomPointsGenerator.printFormattedOutput();

        RandomCentroidsPicker randomCentroidsPicker = new RandomCentroidsPicker(numberOfCentroids, pointVector);
        Vector<Point> centroidVector = randomCentroidsPicker.getCentroidVector();
//        System.out.println("-----------------------------------");
//        System.out.println("Random Centroids (Iteration 0)");
//        System.out.println("-----------------------------------");
//        for(Point centroid : centroidVector) {
//            System.out.println(centroid.toString());
//        }

        Map<Point, Vector<Point>> clusterMap;
        int clusteringIteration = 1;
        while(true) {
            String clusterIterationString = "Iteration [" + clusteringIteration + "]";

            System.out.println(clusterIterationString);
            ClusterGenerator clusterGenerator = new ClusterGenerator(pointVector, centroidVector);
            clusterMap = clusterGenerator.getClusterMap();

            if(plotEveryIteration == true) {
                ScatterPlotGenerator scatterPlotGenerator = new ScatterPlotGenerator(clusterIterationString, clusterMap);
                scatterPlotGenerator.generateScatterPlot();
            }
//            System.out.println("-----------------------------------");
//            System.out.println("Cluster Data");
//            System.out.println("-----------------------------------");
//            clusterGenerator.printFormattedOutput();

            Vector<Point> centroidVectorCalculatedFromClusters = clusterGenerator.getVectorCentroidFromClusters();
//            System.out.println("-----------------------------------");
//            System.out.println("Centroids Calculated From Clusters (Iteration " + clusteringIteration + ")");
//            System.out.println("-----------------------------------");
//            for(Point centroid : centroidVectorCalculatedFromClusters) {
//                System.out.println(centroid.toString());
//            }

            if(ClusterUtility.haveCentroidsConverged(centroidVector, centroidVectorCalculatedFromClusters) == true) {
                break;
            }
            clusteringIteration++;
            centroidVector = centroidVectorCalculatedFromClusters;
        }

        ScatterPlotGenerator scatterPlotGeneratorFinal = new ScatterPlotGenerator("Cluster Final", clusterMap);
        scatterPlotGeneratorFinal.generateScatterPlot();
    }
}
