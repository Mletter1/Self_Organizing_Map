package Core;

/**
 * Created by matthewletter on 11/8/14.
 */
public class Neuron {
    private EVector weights;
    private int x1;
    private int x2;

    /**
     * everything we need to make a neuron
     * @param x1
     * @param x2
     * @param numWeights
     */
    public Neuron(int x1, int x2, int numWeights) {
        this.x1 =x1;
        this.x2 =x2;
        weights = new EVector();
        for (int i=0; i<numWeights; i++) weights.addElement(Math.random()-0.5);//random weight between -.5 and .5
    }

    /**
     * get X1
     * @return
     */
    public int getX1() {
        return x1;
    }

    /**
     * get X2
     * @return
     */
    public int getX2() {
        return x2;
    }

    /**
     * get weight vector
     * @return
     */
    public EVector getW() {
        return weights;
    }

    /**
     * get the distance between two neurons
     * @param n2
     * @return
     */
    public double distance(Neuron n2) {
        int x1D;
        int x2D;
        x1D = (getX1() - n2.getX1())*(getX1() - n2.getX1());
        x2D = (getX2() - n2.getX2())*(getX2() - n2.getX2());
        return Math.sqrt(x1D + x2D);
    }

    /**
     * adjust neuron weight based on a given input
     * @param input
     * @param learningRate
     * @param rad
     * @return
     */
    public double adjustWeights(EVector input, double learningRate,
                              double rad){
        double t = 0;
        double v = 0;
        double change = 0;
        for (int w=0; w<weights.size(); w++) {
            t = (Double)weights.elementAt(w);
            v = (Double)input.elementAt(w);
            change += rad * learningRate * (v - t);
            t += rad * learningRate * (v - t);
            weights.setElementAt(t, w);
        }
        return change/weights.size();
    }
}
