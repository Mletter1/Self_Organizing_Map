package Core;

import java.util.Vector;

/**
 * Created by matthewletter on 11/8/14.
 * modified vector to simplify input output
 */
public class EVector extends Vector {
    public double eDistance(EVector v2){
        double totalDis = 0;
        double dis;
        for (int x=0; x<size(); x++) {
            dis = (Double) elementAt(x) - (Double) v2.elementAt(x);
            dis = dis*dis;
            totalDis += dis;
        }
        return totalDis;
    }
}
