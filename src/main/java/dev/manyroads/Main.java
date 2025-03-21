package dev.manyroads;

import com.sun.security.jgss.GSSUtil;
import dev.manyroads.abstractclass.ImplAbstract;
import dev.manyroads.abstractclass.TestAbstract;

public class Main {
    public static void main(String[] args) {
        Complex c1 = new Complex(10, 4);
        Complex c2 = new Complex(6, 6);
        c1.add(c2);
        System.out.println(c1);
        c1.substract(c2);
        System.out.println(c1);
    }
}

class Complex {
    double real;
    double image;

    public Complex(double real, double image) {
        this.real = real;
        this.image = image;
    }

    public void add(Complex complex) {
        this.real += complex.getReal();
        this.image += complex.getImage();
    }

    public void substract(Complex complex) {
        this.real -= complex.getReal();
        this.image -= complex.getImage();
    }

    public double getReal() {
        return real;
    }

    public double getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Complex{" +
                "real=" + real +
                ", image=" + image +
                '}';
    }
}