package dev.manyroads.serializable.therangeclass.example1;

import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Represents inclusive integer range.
 */
class Range implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @serial
     */
    private final int from;
    /**
     * @serial
     */
    private final int to;

    /**
     * Creates Range.
     *
     * @param from start
     * @param to   end
     * @throws IllegalArgumentException if start is greater than end.
     */
    public Range(int from, int to) {
        checkFromTo(from, to);
        this.from = from;
        this.to = to;
    }

    private void checkFromTo(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("Start is greater than end");
        }
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    private void readObject(ObjectInputStream ois) throws Exception {
        ois.defaultReadObject();
        checkFromTo(from, to);
    }

}