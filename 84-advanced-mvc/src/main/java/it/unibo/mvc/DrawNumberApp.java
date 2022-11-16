package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private static final String CFG_FILE_RELATIVE_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources";
    private static final String CFG_FILE_NAME = "config.yml";
    private static final String CFG_FILE_ABSOLUTE_PATH =
        PROJECT_DIR + SEPARATOR + CFG_FILE_RELATIVE_PATH + SEPARATOR + CFG_FILE_NAME;

    private static final String OUTPUT_FILE_PATH = System.getProperty("user.home");
    private static final String OUTPUT_FILE_NAME = "output.txt";

    private int min = 0;
    private int max = 100;
    private int attempts = 10;

    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param views
     *            the views to attach
     */
    public DrawNumberApp(final DrawNumberView... views) {
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        this.readConfigurationSettings();
        this.model = new DrawNumberImpl(min, max, attempts);
    }

    private void readConfigurationSettings() {
        try (BufferedReader br = new BufferedReader(new FileReader(CFG_FILE_ABSOLUTE_PATH))) {
            min = Integer.parseInt(br.readLine().split(":")[1].trim());
            max = Integer.parseInt(br.readLine().split(":")[1].trim());
            attempts = Integer.parseInt(br.readLine().split(":")[1].trim());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp(new DrawNumberViewImpl(),
                          new DrawNumberViewImpl(),
                          new PrintStreamView(
                            new PrintStream(
                                new FileOutputStream(OUTPUT_FILE_PATH + SEPARATOR + OUTPUT_FILE_NAME))),
                          new PrintStreamView(System.out),
                          new PrintStreamView(System.out));
    }

}
