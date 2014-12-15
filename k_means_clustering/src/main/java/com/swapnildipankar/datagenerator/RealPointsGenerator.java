package com.swapnildipankar.datagenerator;

import com.swapnildipankar.app.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by sdipankar on 12/4/14.
 */
public class RealPointsGenerator implements DataGenerator {
    private String dataFileName;

    public RealPointsGenerator(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    @Override
    public Vector<Point> getDataPoints() {
        Vector<Point> pointVector = new Vector<Point>();
        try {
            String currentWorkingDirectory = System.getProperty("user.dir");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFileName));
            try {
                String currentLine;
                while((currentLine = bufferedReader.readLine()) != null) {
                    if(!currentLine.startsWith("#")) {
                        StringTokenizer tokenizer = new StringTokenizer(currentLine);
                        while(tokenizer.hasMoreElements()) {
                            double xValue = Double.parseDouble(tokenizer.nextToken());
                            double yValue = Double.parseDouble(tokenizer.nextToken());
                            pointVector.add(new Point(xValue, yValue));
                        }
/*
                        String[] splitString = currentLine.split("");
                        double xValue = Double.parseDouble(splitString[1]);
                        double yValue = Double.parseDouble(splitString[2]);

                        pointVector.add(new Point(xValue, yValue));
*/
                    }
                }
            } finally {
                bufferedReader.close();
            }
        } catch(Exception exception) {
            System.out.println("Error: [" + exception.toString() + "]");
        }
        return pointVector;
    }
}
