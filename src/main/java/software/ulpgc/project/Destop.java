package software.ulpgc.project;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.project.viewmode.Histogram;

import javax.swing.*;
import java.awt.*;

public class Destop extends JFrame {
    private Destop() {
        setTitle("Destop");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
    }
    public static Destop create(){
        return new Destop();
    }
    public Destop display(Histogram histogram){
        this.getContentPane().add(chartPanelWith(histogram));
        return this;
    }

    private ChartPanel chartPanelWith(Histogram histogram) {
        return new ChartPanel(chartWith(histogram));
    }

    private JFreeChart chartWith(Histogram histogram) {
        return ChartFactory.createBarChart(histogram.getTitle(), histogram.getX(), histogram.getY(), datasetWith(histogram));
    }

    private CategoryDataset datasetWith(Histogram<? extends Comparable> histogram) {
        DefaultCategoryDataset dataset= new DefaultCategoryDataset();
        for (Histogram.Mold bin: histogram) {dataset.addValue(bin.value(),bin.key(),"");}
        return dataset;
    }
}
