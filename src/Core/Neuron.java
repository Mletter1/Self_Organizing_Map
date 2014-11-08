package Core;

/**
 * Created by matthewletter on 11/8/14.
 */
public class Neuron {
    private WeightVector weights;
    private int xp;
    private int yp;

    public Neuron(int numWeights) {
        weights = new WeightVector();
        for (int i=0; i<numWeights; i++) weights.addElement(Math.random()-0.5);//random weight between -.5 and .5
    }

    public void setX(int xpos) {
        xp = xpos;
    }

    public void setY(int ypos) {
        yp = ypos;
    }

    public int getX() {
        return xp;
    }

    public int getY() {
        return yp;
    }
    public void setWeight(int w, double value) {
        weights.setElementAt(value, w);
    }
    public double getWeight(int w) {
        return (Double) weights.elementAt(w);
    }
    public WeightVector getVector() {
        return weights;
    }

    /** Computes the distance to another node.  Used for
     *  neighborhood determination.  Returns the SQUARE of the distance
     */
    public double distanceTo(Neuron n2) {
        int xDist, yDist;
        xDist = getX() - n2.getX();
        xDist *= xDist;
        yDist = getY() - n2.getY();
        yDist *= yDist;
        return Math.sqrt(xDist + yDist);
    }

    public void adjustWeights(WeightVector input, double learningRate,
                              double distanceFalloff)
    {
        double wt;
        double vw;
        for (int w=0; w<weights.size(); w++) {
            System.err.println(w +" : "+ weights.size());
            wt = ((Double)weights.elementAt(w));
            vw = ((Double)input.elementAt(w));
            wt += distanceFalloff * learningRate * (vw - wt);
            weights.setElementAt(wt, w);
        }
    }
}
