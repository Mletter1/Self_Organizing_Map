package Core;

import java.util.Vector;

/**
 * Created by matthewletter on 11/8/14.
 * modified vector to simplify input output
 */
public class EVector extends Vector {
    public EVector(){
        super();
    }
    /**
     * calucalate euclidean distance between this vector and a given vecotr of the
     * same dimentions
     * @param v2 other vector
     * @return euclidean distance as a double value
     */
    public double euclideanDist(EVector v2){
        double sum = 0;
        double xiMinusYi;
        for (int x=0; x<size(); x++) {
            xiMinusYi = ((Double)elementAt(x)).doubleValue() - ((Double)v2.elementAt(x)).doubleValue();
            xiMinusYi = xiMinusYi*xiMinusYi;
            sum = sum + xiMinusYi;
        }
        return sum;
    }
}
