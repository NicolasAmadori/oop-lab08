package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    /**
     * Initialize the GUI.
     * @param controller is the controller who manage the file actions
     */
    public SimpleGUI(final Controller controller) {
        frame.setTitle("My First Graphical Interface");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextArea textArea = new JTextArea();
        final JButton btnSave = new JButton("Save");

        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        canvas.add(textArea, BorderLayout.CENTER);
        canvas.add(btnSave, BorderLayout.SOUTH);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.saveText(textArea.getText());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });

        frame.getContentPane().add(canvas);
    }

    /**
     * Show the GUI.
     */
    public void show() {
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Creates a new gui and shows it.
     * @param args variable arguments
     */
    public static void main(final String... args) {
        final SimpleGUI interfaccia = new SimpleGUI(new Controller());
        interfaccia.show();
    }
}
