package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    /**
     * Initialize a simple GUI.
     * @param controller controller which manage the I/O
     */
    public SimpleGUI(final Controller controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextField txtField = new JTextField();
        final JTextArea txtArea = new JTextArea();

        final JPanel belowPanel = new JPanel();
        final JButton btnPrint = new JButton("Print");
        final JButton btnShowHistory = new JButton("Show History");
        belowPanel.add(btnPrint);
        belowPanel.add(btnShowHistory);

        canvas.add(txtField, BorderLayout.NORTH);
        canvas.add(txtArea, BorderLayout.CENTER);
        canvas.add(belowPanel, BorderLayout.SOUTH);

        frame.setTitle("Example of I/O interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);

        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setNext(txtField.getText());
                controller.printNext();
            }
        });

        btnShowHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                txtArea.setText("");
                for (final String s : controller.getHistory()) {
                    txtArea.setText(txtArea.getText() + s + "\n");
                }
            }
        });
    }

    /**
     * Show the GUI.
     */
    public void show() {
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Initialize a new GUI and show it.
     * @param args variable arguments
     */
    public static void main(final String... args) {
        final SimpleGUI gui = new SimpleGUI(new SimpleController());
        gui.show();
    }
}
