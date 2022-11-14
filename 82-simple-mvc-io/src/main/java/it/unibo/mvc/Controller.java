package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    public final String DEFAULT_CURRENT_FILE_PATH = System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt";
    private File currentFile = new File(DEFAULT_CURRENT_FILE_PATH);
    
    public void setCurrentFile(File file) {
        this.currentFile = file;
    }

    public File getCurrentFile() {
        return this.currentFile;
    }

    public String getCurrentFilePath() {
        return this.currentFile.getPath();
    }

    public void SaveText(String s) throws IOException{
        try (PrintStream ps = new PrintStream(this.getCurrentFilePath(), StandardCharsets.UTF_8)){
            ps.print(s);
            ps.close();
        }//Serve un catch? chiediamo a pianini
    }
}
