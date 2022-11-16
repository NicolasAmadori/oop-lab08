package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();

    /**
     * Initialize the GUI.
     * @param controller is the controller which manage the file
     */
    public SimpleGUIWithFileChooser(final Controller controller) {
        frame.setTitle("My First Graphical Interface");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextField txtFileName = new JTextField();
        txtFileName.setText(controller.getCurrentFilePath());
        txtFileName.setEditable(false);

        final JTextArea textArea = new JTextArea();
        final JButton btnSave = new JButton("Save");
        final JButton btnBrowse = new JButton("Browse...");

        final JPanel browsePanel = new JPanel();
        browsePanel.setLayout(new BorderLayout());
        browsePanel.add(txtFileName, BorderLayout.CENTER);
        browsePanel.add(btnBrowse, BorderLayout.LINE_END);

        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        canvas.add(browsePanel, BorderLayout.NORTH);
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

        btnBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                final int result = fileChooser.showSaveDialog(fileChooser);
                if (result == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrentFile(fileChooser.getSelectedFile());
                    txtFileName.setText(controller.getCurrentFilePath());
                } else if (result != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(canvas, "Error occured while browsing files");
                }
            }
        });

        frame.getContentPane().add(canvas);
    }

    /**
     * show the GUI.
     */
    public void show() {
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Creates a new GUI and shows it.
     * @param args variable arguments
     */
    public static void main(final String... args) {
        final SimpleGUIWithFileChooser interfaccia = new SimpleGUIWithFileChooser(new Controller());
        interfaccia.show();
    }
}
