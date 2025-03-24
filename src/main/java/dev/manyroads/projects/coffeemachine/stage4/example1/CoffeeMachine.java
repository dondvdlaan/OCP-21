package dev.manyroads.projects.coffeemachine.stage4.example1;

public class CoffeeMachine {
    public static void main(String[] args) {
        Machine machine = new Machine(400, 540, 120, 9, 550);
        machine.status();
        machine.processAction();
        machine.status();
    }
}
