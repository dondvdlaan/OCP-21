package dev.manyroads.abstractclass;

public abstract class TestAbstract {

    abstract String geeftStringTerug(String textje);

    public void printje(String textje) {
        System.out.println(geeftStringTerug(textje));
    }
}
