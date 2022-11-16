package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String DEFAULT_ROOT = System.getProperty("user.home") + System.getProperty("file.separator");
    private static final String DEFAULT_CURRENT_FILE_PATH = DEFAULT_ROOT + "output.txt";
    private File currentFile = new File(DEFAULT_CURRENT_FILE_PATH);

    /**
     * Change the current file managed by the controller.
     * @param file The file which is set as the current file
     */
    public void setCurrentFile(final File file) {
        this.currentFile = file;
    }

    /**
     * Get the current file.
     * @return is the current file
     */
    public File getCurrentFile() {
        return this.currentFile;
    }

    /**
     * Get the path of the current file.
     * @return is the path of the current file
     */
    public String getCurrentFilePath() {
        return this.currentFile.getPath();
    }

    /**
     * Replace the current text in the current file with the new text passed as paramether.
     * @param s the new text which replace the old text into the file
     * @throws IOException 
     */
    public void saveText(final String s) throws IOException {
        try (PrintStream ps = new PrintStream(this.getCurrentFilePath(), StandardCharsets.UTF_8)) {
            ps.print(s);
        }
    }
}
