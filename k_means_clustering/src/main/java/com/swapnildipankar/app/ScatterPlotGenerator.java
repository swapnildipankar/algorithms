package com.swapnildipankar.app;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Map;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;

import javax.swing.*;

/**
 * Created by sdipankar on 12/1/14.
 */
public class ScatterPlotGenerator extends ApplicationFrame {
    private String title;
    private Map<Point, Vector<Point>> clusterMap;
    private JPanel jPanel;

    public ScatterPlotGenerator(final String title, Map<Point, Vector<Point>> clusterMap) {
        super(title);

        this.title = title;
        this.clusterMap = clusterMap;

        jPanel = createDemoPanel();
        jPanel.setPreferredSize(new Dimension(900, 600));
        add(jPanel);
    }

    private JPanel createDemoPanel() {
        JFreeChart jFreeChart = ChartFactory.createScatterPlot(
                "K-Means Clustering",
                "X",
                "Y",
                createDataSetFromClusterMap(),
                PlotOrientation.VERTICAL,
                false/*true*/,
                false/*true*/,
                false);
        XYPlot xyPlot = (XYPlot) jFreeChart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        for(int index = 0; index < this.clusterMap.size(); ++index) {
            renderer.setSeriesShape(index, new Ellipse2D.Double(-2.0, -2.0, 4.0, 4.0));
        }
        renderer.setSeriesShape(this.clusterMap.size(), ShapeUtilities.createDiagonalCross(3, 1));
        renderer.setSeriesPaint(this.clusterMap.size(), Color.BLACK);

        return new ChartPanel(jFreeChart);
    }

    private XYDataset createDataSetFromClusterMap() {
        final XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        int clusterCount = 1;
        final XYSeries centroidDataSeries = new XYSeries("Centroids");
        for(Map.Entry<Point, Vector<Point>> entry : clusterMap.entrySet()) {
            Point centroid = entry.getKey();
            centroidDataSeries.add(centroid.getX(), centroid.getY());

            final XYSeries xySeries = new XYSeries("Cluster " + clusterCount++);
            Vector<Point> pointVector = entry.getValue();
            for(Point point : pointVector) {
                xySeries.add(point.getX(), point.getY());
            }
            xySeriesCollection.addSeries(xySeries);
        }
        xySeriesCollection.addSeries(centroidDataSeries);
        return xySeriesCollection;
    }

    public void generateScatterPlot() {
        ScatterPlotGenerator scatterPlotGenerator = new ScatterPlotGenerator(title, clusterMap);
        scatterPlotGenerator.pack();
        RefineryUtilities.centerFrameOnScreen(scatterPlotGenerator);
        scatterPlotGenerator.setVisible(true);
    }
}
