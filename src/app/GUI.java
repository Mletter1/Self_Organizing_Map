package app;

import Core.NeuronMatrix;

import javax.swing.*;
import java.awt.*;

/**
 * Created by matthewletter on 11/8/14.
 */
public class GUI extends JPanel{
    private NeuronMatrix matrix;
    private int windowSize;
    private int M;
    public GUI(NeuronMatrix matrix, int size, int m) {
        this.matrix = matrix;
        this.windowSize = size;
        M = m;
    }
    public void paintComponent(Graphics g){
        //gotta make that nice white background
        super.paintComponent(g);
        paintHelperFun(g);
    }
    public void paintHelperFun(Graphics g){
        float r;
        float gr;
        float b;
        for(int x = 0;x < M;x ++){
            for (int y = 0; y < M; y++) {
                r = (float)((Double)matrix.getNeuron(x, y).getW().elementAt(0)).doubleValue();
                gr = (float)((Double)matrix.getNeuron(x, y).getW().elementAt(1)).doubleValue();
                r = (float)Math.sqrt(r*r)/2;
                gr = (float)Math.sqrt(gr*gr)/2;
                b = 0;
                g.setColor(new Color(b,r,gr));
                g.fill3DRect(x*(windowSize/M),y*(windowSize/M),windowSize/M,windowSize/M,true);
            }
        }
    }
}
