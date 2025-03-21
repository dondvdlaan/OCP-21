package dev.manyroads.abstractclass;

public class ImplAbstract extends TestAbstract {
    @Override
    public String geeftStringTerug(String textje) {
        return textje;
    }

    public static void main(String[] args) {
        TestAbstract t = new ImplAbstract();
        System.out.println(t.geeftStringTerug("aah"));
    }
}
