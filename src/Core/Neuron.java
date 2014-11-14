package Core;

/**
 * Created by matthewletter on 11/8/14.
 */
public class Neuron {
    private EVector weights;
    private int x1;
    private int x2;

    public Neuron(int x1, int x2, int numWeights) {
        this.x1 =x1;
        this.x2 =x2;
        weights = new EVector();
        for (int i=0; i<numWeights; i++) weights.addElement(Math.random()-0.5);//random weight between -.5 and .5
    }

    public void setX(int xpos) {
        x1 = xpos;
    }

    public void setY(int ypos) {
        x2 = ypos;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }
    public void setWeight(int w, double value) {
        weights.setElementAt(value, w);
    }
    public double getWeight(int w) {
        return (Double) weights.elementAt(w);
    }
    public EVector getWeights() {
        return weights;
    }

    public double distance(Neuron n2) {
        int xDist;
        int yDist;
        xDist = (getX1() - n2.getX1())*(getX1() - n2.getX1());
        yDist = (getX2() - n2.getX2())*(getX2() - n2.getX2());
        return Math.sqrt(xDist + yDist);
    }

    public double adjustWeights(EVector input, double learningRate,
                              double rad)
    {
        double wt;
        double vw;
        double change = 0;
        for (int w=0; w<weights.size(); w++) {
            wt = ((Double)weights.elementAt(w));
            vw = ((Double)input.elementAt(w));
            change += rad * learningRate * (vw - wt);
            wt += rad * learningRate * (vw - wt);;
            weights.setElementAt(wt, w);
        }
        return change/weights.size();
    }
}
