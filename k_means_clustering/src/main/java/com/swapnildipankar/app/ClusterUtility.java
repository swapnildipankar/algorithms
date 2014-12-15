package com.swapnildipankar.app;

import java.util.Vector;

/**
 * Created by sdipankar on 12/1/14.
 */
public class ClusterUtility {
    private static double precision = 0.001;

    public static boolean haveCentroidsConverged(Vector<Point> centroidVectorA, Vector<Point> centroidVectorB) {
        if(centroidVectorA.size() != centroidVectorB.size()) {
            return false; // TODO: throw IllegalArgumentException
        }

        for(int index = 0; index < centroidVectorA.size(); index++) {
            Point centroidA = centroidVectorA.elementAt(index);
            Point centroidB = centroidVectorB.elementAt(index);

            if(Math.abs(centroidA.getX() - centroidB.getX()) > precision) {
                return false;
            }
            if(Math.abs(centroidA.getY() - centroidB.getY()) > precision) {
                return false;
            }
        }
        return true;
    }
}
