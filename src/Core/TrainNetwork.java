package Core;


import app.App;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * Created by matthewletter on 11/8/14.
 */
public class TrainNetwork{
    private double ETA;
    private int MAX_ITERATIONS;
    private double sigma0;
    private double tao;


    private NeuronMatrix neuronMatrix;
    private Vector inputs;
    private static boolean running;
    private Thread runner;

    public TrainNetwork(double eta, int max) {
        this.ETA = eta;
        this.MAX_ITERATIONS = max;
        running = false;
    }

    private double getNeighborhoodRadius(double iteration) {
        return sigma0 * Math.exp(-iteration/ tao);
    }

    private double neighborhoodFunction(double distSq, double radius) {
        double radiusSq = radius * radius;
        return Math.exp(-(distSq)/(2 * radiusSq));
    }

    public void setTraining(NeuronMatrix latToTrain, Vector in)
    {
        neuronMatrix = latToTrain;
        inputs = in;
    }

    public void run(App app) {
        int xstart, ystart, xend, yend;
        double dist;
        double rad = 0;
        double sumErr=0;
        double magChange=0;
        sigma0 = neuronMatrix.getM()/2;
        tao = MAX_ITERATIONS / Math.log(sigma0);
        running = true;
        int iteration = 0;
        double nbhRadius=0;
        Neuron bmu = null;
        Neuron temp = null;
        EVector curInput = null;
        double learningRate = ETA;
        double time = System.currentTimeMillis();
        Random myRandom = new Random(System.nanoTime());
        while (iteration < 10000 && running) {
            sumErr=0;
            nbhRadius = getNeighborhoodRadius(iteration);
            for (int i=0; i<inputs.size(); i++) {
                curInput = (EVector)inputs.elementAt(myRandom.nextInt(inputs.size()));
                bmu = neuronMatrix.getBMU(curInput);
                xstart = (int)(bmu.getX1() - nbhRadius - 1);
                ystart = (int)(bmu.getX2() - nbhRadius - 1);
                xend = (int)(xstart + (nbhRadius * 2) + 1);
                yend = (int)(ystart + (nbhRadius * 2) + 1);
                if (xend > neuronMatrix.getM()) xend = neuronMatrix.getM();
                if (xstart < 0) xstart = 0;
                if (yend > neuronMatrix.getM()) yend = neuronMatrix.getM();
                if (ystart < 0) ystart = 0;

                for (int x=xstart; x<xend; x++) {
                    for (int y=ystart; y<yend; y++) {
                        temp = neuronMatrix.getNeuron(x, y);
                        dist = bmu.distance(temp);
                        if (dist <= (nbhRadius * nbhRadius)) {
                            rad = neighborhoodFunction(dist, nbhRadius);
                            sumErr += temp.adjustWeights(curInput, learningRate, rad);
                        }
                    }
                }
            }
            magChange=Math.sqrt((sumErr*sumErr)/inputs.size());
            System.err.println(magChange);

            if(magChange<0.00001){
                running = false;
            }
            if(iteration%2==0){
                //System.err.println(magChange);
                app.updateView(neuronMatrix, iteration, magChange);
            }
            iteration++;
            learningRate = ETA * Math.exp(-(double)iteration/ MAX_ITERATIONS);
        }

        running = false;
        System.out.println("ran in :" + ((System.currentTimeMillis() - time) / 1000) + " seconds");
        System.err.println("done"+iteration);
    }
}
