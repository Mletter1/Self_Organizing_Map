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
