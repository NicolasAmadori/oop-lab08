package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {
    private String next;
    private final List<String> history = new ArrayList<>(0);

    @Override
    public void setNext(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Null is not a valid value");
        }
        this.next = s;
    }

    @Override
    public String getNext() {
        return this.next;
    }

    @Override
    public List<String> getHistory() {
        return List.copyOf(history);
    }

    @Override
    public void printNext() {
        if (this.next == null) {
            throw new IllegalStateException("Value has not been set");
        }
        System.out.println(this.next); //NOPMD: println is required
        history.add(this.next);
    }
}
