package Core;


import java.util.Vector;

/**
 * Created by matthewletter on 11/8/14.
 */
public class TrainNetwork{
    // These constants can be changed to play with the learning algorithm
    private static double ETA;
    private static int MAX_ITERATIONS;
    private double sigma0;
    private double tao;


    private NeuronMatrix neuronMatrix;
    private Vector inputs;
    private static boolean running;
    private Thread runner;

    /** Creates a new instance of SOMTrainer */
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

    // Train the given neuronMatrix based on a vector of input vectors
    public void setTraining(NeuronMatrix latToTrain, Vector in)
    {
        neuronMatrix = latToTrain;
        inputs = in;
    }

    public void run() {
        int xstart, ystart, xend, yend;
        double dist, dFalloff;
        // These two values are used in the training algorithm
        sigma0 = Math.max(neuronMatrix.getM(), neuronMatrix.getM())/2;
        tao = MAX_ITERATIONS / Math.log(sigma0);
        running = true;
        int iteration = 0;
        double nbhRadius;
        Neuron bmu = null;
        Neuron temp = null;
        EVector curInput = null;
        double learningRate = ETA;

        while (iteration < MAX_ITERATIONS && running) {
            nbhRadius = getNeighborhoodRadius(iteration);
            // For each of the input vectors, look for the best matching
            // unit, then adjust the weights for the BMU's neighborhood
            for (int i=0; i<inputs.size(); i++) {
                curInput = (EVector)inputs.elementAt(i);
                bmu = neuronMatrix.getBMU(curInput);
                // We have the BMU for this input now, so adjust everything in
                // it's neighborhood

                // Optimization:  Only go through the X/Y values that fall within
                // the radius
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
                            dFalloff = neighborhoodFunction(dist, nbhRadius);
                            temp.adjustWeights(curInput, learningRate, dFalloff);
                        }
                    }
                }
            }
            iteration++;
            learningRate = ETA * Math.exp(-(double)iteration/ MAX_ITERATIONS);
        }
        running = false;
        System.err.println("done"+iteration);
    }
}
