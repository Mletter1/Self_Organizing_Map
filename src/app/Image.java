package app;

import javax.swing.*;

/**
 * Created by matthewletter on 11/8/14.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Swing Program Template
@SuppressWarnings("serial")
public class Image extends JPanel {
    // Name-constants
    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 400;
    public static final String TITLE = "---SOM---";
    // ......

    // private variables of GUI components
    // ......

    /** Constructor to setup the GUI components */
    public Image() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        // "this" JPanel container sets layout
        // setLayout(new ....Layout());

        // Allocate the UI components
        // .....

        // "this" JPanel adds components
        // add(....)

        // Source object adds listener
        // .....
    }

    /** Custom painting codes on this JPanel */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // paint background
        setBackground(Color.BLACK);

        // Your custom painting codes
        // ......
    }

    /** The entry main() method */
    public static void main(String[] args) {
        // Run GUI codes in the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(TITLE);
                frame.setContentPane(new App());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();             // "this" JFrame packs its components
                frame.setLocationRelativeTo(null); // center the application window
                frame.setVisible(true);            // show it
            }
        });
    }
}
