package app;

import Core.EVector;
import Core.NeuronMatrix;
import Core.TrainNetwork;
import util.ImportData;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Vector;

public class App{
    private final int M = 100;
    private final int NUM_INPUT = 10;
    private static double ETA = 0.07;
    private static int MAX_ITERATIONS = 1000;

    private TrainNetwork trainNetwork;
    private GUI gui;

    NeuronMatrix matrix = null;
    Vector<EVector> dataVectors;
    Vector<EVector> inputVectors = new Vector<EVector>();

    public App() {
        matrix = new NeuronMatrix(M);
        trainNetwork = new TrainNetwork(ETA,MAX_ITERATIONS);
        //getData
        ImportData data = new ImportData("/Users/matthewletter/Documents/Self_Organizing_Map/data/Data");
        System.err.println(data.toString());
        dataVectors = data.getData();
        Collections.shuffle(dataVectors);

        //get some data
        for (int i = 0; i < NUM_INPUT; i++) {
            inputVectors.add(dataVectors.get(i));
        }
        trainNetwork.setTraining(matrix, inputVectors);
        trainNetwork.run();
    }
    public void outPut(){
        double w1;
        double w2;
        int dim = 800;
        for (int x=0; x<M; x++) {
            for (int y=0; y<M; y++) {
                w1 = ((Double)matrix.getNeuron(x,y).getWeights().elementAt(0)).doubleValue();
                w2 = ((Double)matrix.getNeuron(x,y).getWeights().elementAt(1)).doubleValue();
                //b = (float)((Double)lattice.getNode(x,y).getVector().elementAt(2)).doubleValue();
                System.out.println("w1="+w1+" w2="+w2);
            }
        }
        JFrame mainFrame = new JFrame("out");
        JPanel shapePanel = new GUI(matrix,dim,M);
        shapePanel.setBackground(Color.WHITE);
        shapePanel.setPreferredSize(new Dimension(dim, dim));
        // add the JPanel to the pane
        mainFrame.getContentPane().add(shapePanel, BorderLayout.CENTER);
        // clean up
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);
    }
    public static void main(String args[]) {
        App app = new App();
        app.outPut();
    }
}