package Core;

/**
 * Created by matthewletter on 11/8/14.
 */
public class NeuronMatrix {
    private int m;
    private Neuron[][] matrix;

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

    public Neuron getBMU(EVector inputVector) {
        Neuron bmu = matrix[0][0];
        double bestDist = inputVector.euclideanDist(bmu.getWeights());
        double curDist;
        for (int x=0; x<m; x++) {
            for (int y=0; y<m; y++) {
                curDist = inputVector.euclideanDist(matrix[x][y].getWeights());
                if (curDist < bestDist) {
                    bmu = matrix[x][y];
                    bestDist = curDist;
                }
            }
        }
        return bmu;
    }
}
