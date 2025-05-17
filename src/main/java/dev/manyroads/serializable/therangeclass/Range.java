package dev.manyroads.serializable.therangeclass;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents inclusive integer range.
 * The Range class is supposed to represent an integer range. Its invariants restrict the start and the end
 * of the range so that the value of the from field is not greater than the one of the to field.
 * <p>
 * Unfortunately, there's a chance that the invariants can break during serialization/deserialization.
 * You need to improve the class so that it preserves the invariants during deserialization (i.e. from <= to).
 * When the deserialized object breaks the invariant, throw IllegalArgumentException.
 * Pay attention to the constructor since it provides a good example of the required behavior.
 */
class Range implements Serializable {

    @Serial
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
        if (from > to) {
            throw new IllegalArgumentException("Start is greater than end");
        }
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        if (from > to) {
            throw new IllegalArgumentException("Start is greater than end");
        }
        return from;
    }

    public int getTo() {
        return to;
    }
}

class Main {

    public static void main(String[] args) {
        Range range = new Range(10, 35);
        try {
            serialize(range, "rangeFile");
            Object obj = deserialize("rangeFile");
            if(obj instanceof Range range1){
                System.out.println(range.getFrom() +" "+ range1.getTo());
            }
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void serialize(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Range returnRange = null;
        try {
            Object obj = ois.readObject();
            if (obj instanceof Range range) {
                if (range.getFrom() > range.getTo()) {
                    throw new IllegalArgumentException("Start is greater than end");
                }
                returnRange = range;
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Continue after IllegalArgumentException");
        }
        ois.close();
        return returnRange;
    }
}
