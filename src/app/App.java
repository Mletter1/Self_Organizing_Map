package app;

import Core.EVector;
import Core.LinesComponent;
import Core.NeuronMatrix;
import Core.TrainNetwork;
import util.ImportData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

public class App{
    /****************************************
     * vary the next 4 variables for study
     *
     ****************************************/
    private final int M = 15;
    private final int NUM_INPUT = 1600;
    private static double ETA = 0.01;
    private static int MAX_ITERATIONS = 10000;

    private TrainNetwork trainNetwork;
    private GUI gui;

    //gui
    JFrame testFrame = new JFrame();
    LinesComponent comp =null;
    JPanel buttonsPanel = new JPanel();
    JButton newLineButton = new JButton("stop");
    JButton clearButton = new JButton("start");
    //end gui

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
        outPut(matrix);
        Collections.shuffle(dataVectors);

        //get some data
        for (int i = 0; i < NUM_INPUT; i++) {
            inputVectors.add(dataVectors.get(i));
        }
        trainNetwork.setTraining(matrix, inputVectors);
        trainNetwork.run(this);

    }
    public void outPut(NeuronMatrix m){
//        double w1;
//        double w2;
//
//        for (int x=0; x<M; x++) {
//            for (int y=0; y<M; y++) {
//                w1 = ((Double)matrix.getNeuron(x,y).getWeights().elementAt(0)).doubleValue();
//                w2 = ((Double)matrix.getNeuron(x,y).getWeights().elementAt(1)).doubleValue();
//                //b = (float)((Double)lattice.getNode(x,y).getVector().elementAt(2)).doubleValue();
//                System.out.println("w1="+w1+" w2="+w2);
//            }
//        }
//        int dim = 800;
//        JFrame mainFrame = new JFrame("out");
//        JPanel shapePanel = new GUI(matrix,dim,M);
//        shapePanel.setBackground(Color.WHITE);
//        shapePanel.setPreferredSize(new Dimension(dim, dim));
//        // add the JPanel to the pane
//        mainFrame.getContentPane().add(shapePanel, BorderLayout.CENTER);
//        // clean up
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame.pack();
//        mainFrame.setResizable(true);
//        mainFrame.setVisible(true);

        //other
        comp = new LinesComponent(m,M,dataVectors);
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        comp.setPreferredSize(new Dimension(800, 800));
        testFrame.getContentPane().add(comp, BorderLayout.CENTER);
        buttonsPanel.add(newLineButton);
        buttonsPanel.add(clearButton);
        testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        newLineButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comp.stopStart(false);
            }
        });
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comp.stopStart(true);
            }
        });
        testFrame.pack();
        testFrame.setVisible(true);
    }
    public void updateView(NeuronMatrix m, int iteration, double magChange){
        comp.updateMatrix(m,iteration,magChange);
    }
    public static void main(String args[]) {

        App app = new App();
        //app.outPut();
    }
}