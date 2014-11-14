package Core;


import app.App;

import java.util.Random;
import java.util.Vector;

/**
 * Created by matthewletter on 11/8/14.
 */
public class TrainNetwork{
    private double ETA = 0;
    private int taoDependancyFactor = 0;
    private double sigma0 = 0;
    private double tao = 0;
    private NeuronMatrix neuronMatrix = null;
    private Vector<EVector> inputs = null;
    /**
     * provide the train function with some basic data
     * @param eta
     * @param t
     */
    public TrainNetwork(double eta, int t) {
        this.ETA = eta;
        this.taoDependancyFactor =t;
    }

    /**
     * return the rad of the neighborhood
     * @param iteration
     * @return
     */
    private double getNeighborhoodRadius(double iteration) {
        return sigma0 * Math.exp(-iteration/ tao);
    }

    private double neighborhoodFunction(double distSq, double radius) {
        double radiusSq = radius * radius;
        return Math.exp(-(distSq)/(2 * radiusSq));
    }

    /**
     * set some data points of the trainning
     * @param latToTrain
     * @param in
     */
    public void setTraining(NeuronMatrix latToTrain, Vector<EVector> in)
    {
        neuronMatrix = latToTrain;
        inputs = in;
        sigma0 = neuronMatrix.getM()/20;
        tao = taoDependancyFactor / Math.log(sigma0);
    }

    /**
     * start trainning the network
     * @param app
     */
    public void startTraining(App app) {

        double dist;
        double rad = 0;
        double sumErr=0;
        double magChange=0;
        int iteration = 0;
        double r=0;
        Neuron winningNeuron = null;
        Neuron currentNeuron = null;
        int xi=0;
        int yi=0;
        int xf=0;
        int yf=0;
        EVector curInput = null;
        double learningRate = ETA;
        double time = System.currentTimeMillis();
        Random myRandom = new Random(System.nanoTime());
        boolean go = true;
        while (iteration < 10000&&go) {
            sumErr=0;
            r = getNeighborhoodRadius(iteration);
            for (int i=0; i<inputs.size(); i++) {
                //get an input data point
                curInput = inputs.elementAt(i);

                //startTraining winner take all on the input and get a neuron back
                winningNeuron = neuronMatrix.getWinnerTakeAll(curInput);

                /*get our indexs of all the points within our radius*/
                xi = (int) (winningNeuron.getX1() - r - 1);
                yi = (int)(winningNeuron.getX2() - r - 1);
                //find the end of the radius
                xf = (int)(xi + (r * 2) + 1);
                yf = (int)(yi + (r * 2) + 1);

                //make sure we are in bounds
                if (xf > neuronMatrix.getM()){
                    xf = neuronMatrix.getM();
                }
                //make sure we didn't go negative
                if (xi < 0) xi = 0;
                //make sure we are in bounds
                if (yf > neuronMatrix.getM()){
                    yf = neuronMatrix.getM();
                }
                //make sure we didn't go negative
                if (yi < 0) yi = 0;
                //loop through all the neurons within range
                for (int x=xi; x<xf; x++) {
                    for (int y=yi; y<yf; y++) {
                        currentNeuron = neuronMatrix.getNeuron(x, y);
                        //get the dist between the neurons
                        dist = winningNeuron.distance(currentNeuron);
                        if (dist <= (r * r)) {
                            rad = neighborhoodFunction(dist, r);
                            //adjust the weights and get a average mag change in return
                            sumErr += currentNeuron.adjustWeights(curInput, learningRate, rad);
                        }
                    }
                }
            }
            magChange=Math.sqrt((sumErr*sumErr)/inputs.size());
            System.err.println(magChange);
            if(magChange<0.000001){
                go = false;
            }
            if(iteration%1==0){
                //System.err.println(magChange);
                app.updateView(neuronMatrix, iteration, magChange);
            }
            iteration++;
            learningRate = ETA * Math.exp(-(double)iteration/ taoDependancyFactor);
        }
        System.out.println("ran in :" + ((System.currentTimeMillis() - time) / 1000) + " seconds");
        System.err.println("done"+iteration);
    }
}
