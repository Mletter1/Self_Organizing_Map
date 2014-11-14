package Core;

/**
 * Created by matthewletter on 11/11/14.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Vector;


public class LinesComponent extends JComponent{
    private NeuronMatrix matrix;
    private int M;
    private int iteration = 0;
    private double magChange = 0;
    private Vector<EVector> dataVectors;
    private boolean stopFlag = true;

    public LinesComponent(NeuronMatrix matrix, int M, Vector<EVector> dataVectors) {
        this.matrix=matrix;
        this.M = M;
        this.dataVectors = dataVectors;
    }

    public void updateMatrix(NeuronMatrix matrix, int iteration, double magChange) {
        if(stopFlag) {
            this.matrix = matrix;
            this.iteration = iteration;
            this.magChange = magChange;
            repaint();
        }
    }

    /**
     * stop/start the visual
     * @param s
     */
    public void stopStart(boolean s){
        stopFlag = s;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double x;
        double y;
        double offset = 0.5;
        double mul = 350;
        double x1 = -1;
        double y1 = -1;
        double x2 = -1;
        double y2 = -1;
        double x3 = -1;
        double y3 = -1;
        double x4 = -1;
        double y4 = -1;
        g.drawString("iteration:" + Integer.toString(iteration), 380, 12);
        g.drawString("magChange:" + Double.toString(magChange), 380, 24);
        g.drawString("X1:", 0, 400);
        g.drawString("X2:", 400, 799);
        for (int i = 0; i < dataVectors.size(); i++) {
            g.setColor(Color.BLUE);
            ((Graphics2D) g).fill(new Ellipse2D.Double(((Double) dataVectors.get(i).elementAt(0) + offset) * mul, ((Double) dataVectors.get(i).elementAt(1) + offset) * mul, 5, 5));
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                g.setColor(Color.BLACK);
                x = (((Double) matrix.getNeuron(i, j).getW().elementAt(0)).doubleValue() + offset) * mul;
                y = (((Double) matrix.getNeuron(i, j).getW().elementAt(1)).doubleValue() + offset) * mul;
                if (i > 0 && i < M - 1 && j > 0 && j < M - 1) {
                    x1 = (((Double) matrix.getNeuron(i - 1, j).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y1 = (((Double) matrix.getNeuron(i - 1, j).getW().elementAt(1)).doubleValue() + offset) * mul;
                    x2 = (((Double) matrix.getNeuron(i + 1, j).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y2 = (((Double) matrix.getNeuron(i + 1, j).getW().elementAt(1)).doubleValue() + offset) * mul;
                    x3 = (((Double) matrix.getNeuron(i, j - 1).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y3 = (((Double) matrix.getNeuron(i, j - 1).getW().elementAt(1)).doubleValue() + offset) * mul;
                    x4 = (((Double) matrix.getNeuron(i, j + 1).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y4 = (((Double) matrix.getNeuron(i, j + 1).getW().elementAt(1)).doubleValue() + offset) * mul;
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x1, y1));
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x2, y2));
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x3, y3));
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x4, y4));
                } else if (i == 0 && j > 0 && j < M - 1) {
                    x2 = (((Double) matrix.getNeuron(i + 1, j).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y2 = (((Double) matrix.getNeuron(i + 1, j).getW().elementAt(1)).doubleValue() + offset) * mul;
                    x3 = (((Double) matrix.getNeuron(i, j - 1).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y3 = (((Double) matrix.getNeuron(i, j - 1).getW().elementAt(1)).doubleValue() + offset) * mul;
                    x4 = (((Double) matrix.getNeuron(i, j + 1).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y4 = (((Double) matrix.getNeuron(i, j + 1).getW().elementAt(1)).doubleValue() + offset) * mul;
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x2, y2));
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x3, y3));
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x4, y4));
                } else if (i == M - 1 && j > 0 && j < M - 1) {
                    x1 = (((Double) matrix.getNeuron(i - 1, j).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y1 = (((Double) matrix.getNeuron(i - 1, j).getW().elementAt(1)).doubleValue() + offset) * mul;
                    x3 = (((Double) matrix.getNeuron(i, j - 1).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y3 = (((Double) matrix.getNeuron(i, j - 1).getW().elementAt(1)).doubleValue() + offset) * mul;
                    x4 = (((Double) matrix.getNeuron(i, j + 1).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y4 = (((Double) matrix.getNeuron(i, j + 1).getW().elementAt(1)).doubleValue() + offset) * mul;
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x3, y3));
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x4, y4));
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x1, y1));
                } else if (j == 0 && i > 0 && i < M - 1) {
                    x1 = (((Double) matrix.getNeuron(i - 1, j).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y1 = (((Double) matrix.getNeuron(i - 1, j).getW().elementAt(1)).doubleValue() + offset) * mul;
                    x2 = (((Double) matrix.getNeuron(i + 1, j).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y2 = (((Double) matrix.getNeuron(i + 1, j).getW().elementAt(1)).doubleValue() + offset) * mul;
                    x4 = (((Double) matrix.getNeuron(i, j + 1).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y4 = (((Double) matrix.getNeuron(i, j + 1).getW().elementAt(1)).doubleValue() + offset) * mul;
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x1, y1));
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x2, y2));
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x4, y4));
                } else if (j == M - 1 && i > 0 && i < M - 1) {
                    x1 = (((Double) matrix.getNeuron(i - 1, j).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y1 = (((Double) matrix.getNeuron(i - 1, j).getW().elementAt(1)).doubleValue() + offset) * mul;
                    x2 = (((Double) matrix.getNeuron(i + 1, j).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y2 = (((Double) matrix.getNeuron(i + 1, j).getW().elementAt(1)).doubleValue() + offset) * mul;
                    x3 = (((Double) matrix.getNeuron(i, j - 1).getW().elementAt(0)).doubleValue() + offset) * mul;
                    y3 = (((Double) matrix.getNeuron(i, j - 1).getW().elementAt(1)).doubleValue() + offset) * mul;
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x1, y1));
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x2, y2));
                    ((Graphics2D) g).draw(new Line2D.Double(x, y, x3, y3));
                }
                g.setColor(Color.BLACK);
                ((Graphics2D) g).fill(new Ellipse2D.Double(x, y, 5, 5));
            }
        }
    }
}