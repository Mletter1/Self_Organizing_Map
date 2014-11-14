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

    /**
     * return a 2d array of the lattice of SOM neurons
     * @param x
     * @param y
     * @return
     */
    public Neuron getNeuron(int x, int y) {
        return matrix[x][y];
    }

    /**
     * return M of the MxM lattice
     * @return
     */
    public int getM(){
        return m;
    }

    /**
     * find out who is the winner of the winner take all
     * @param in
     * @return
     */
    public Neuron getWinnerTakeAll(EVector in) {
        Neuron winningNeuron = matrix[0][0];
        double bestDist = in.eDistance(winningNeuron.getW());
        double curDist=0;
        for(Neuron[] nL: matrix){
            for(Neuron n : nL){
                curDist = in.eDistance(n.getW());
                if (curDist < bestDist) {
                    winningNeuron = n;
                    bestDist = curDist;
                }
            }
        }
        return winningNeuron;
    }
}
