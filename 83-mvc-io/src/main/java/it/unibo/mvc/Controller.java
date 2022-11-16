package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    /**
     * Set the next value to print.
     * @param s value to be set as the next value
     */
    void setNext(String s);

    /**
     * Get the next value to print.
     * @return the next value
     */
    String getNext();

    /**
     * Get a list of all the string printed out in the past.
     * @return List<String> of every string printed out in the past
     */
    List<String> getHistory();

    /**
     * Print the next value in the standard output.
     */
    void printNext();
}
