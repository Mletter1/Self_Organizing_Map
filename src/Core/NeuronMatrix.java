package Core;

/**
 * Created by matthewletter on 11/8/14.
 */
public class NeuronMatrix {
    private int m;
    private Neuron[][] matrix;

    /** Creates a new instance of SOMLattice,
     *  which is a 2x2 array of SOMNodes. For now, it
     *  assumes an input vector of three values, and
     *  randomly initializes the array as such.
     */
    public NeuronMatrix(int m) {
        this.m = m;
        matrix = new Neuron[m][m];
        for (int x=0; x<m; x++) {
            for (int y=0; y<m; y++) {
                matrix[x][y] = new Neuron(x,y,2);
            }
        }
    }

    public Neuron getNeuron(int x, int y) {
        return matrix[x][y];
    }

    public int getM(){
          return m;
      }

    /** Finds the best matching unit for the given
     *  inputVector
     */
    public Neuron getBMU(EVector inputVector) {
        // Start out assuming that 0,0 is our best matching unit
        Neuron bmu = matrix[0][0];
        double bestDist = inputVector.euclideanDist(bmu.getWeights());
        double curDist;

        // Now step through the entire matrix and check the euclidean distance
        // between the input vector and the given node
        for (int x=0; x<m; x++) {
            for (int y=0; y<m; y++) {
                curDist = inputVector.euclideanDist(matrix[x][y].getWeights());
                if (curDist < bestDist) {
                    // If the distance from the current node to the input vector
                    // is less than the distance to our current BMU, we have a
                    // new BMU
                    bmu = matrix[x][y];
                    bestDist = curDist;
                }
            }
        }
        return bmu;
    }
}
