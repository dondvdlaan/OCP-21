package dev.manyroads.projects.coffeemachine.stage4.example1;

public class Receipt {
    private int id;
    private String name;
    private int water;
    private int milk;
    private int coffee;
    private int cost;

    public Receipt(int id, String name, int water, int milk, int coffee, int cost) {
        this.id = id;
        this.name = name;
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public Receipt setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Receipt setName(String name) {
        this.name = name;
        return this;
    }

    public int getWater() {
        return water;
    }

    public Receipt setWater(int water) {
        this.water = water;
        return this;
    }

    public int getMilk() {
        return milk;
    }

    public Receipt setMilk(int milk) {
        this.milk = milk;
        return this;
    }

    public int getCoffee() {
        return coffee;
    }

    public Receipt setCoffee(int coffee) {
        this.coffee = coffee;
        return this;
    }

    public int getCost() {
        return cost;
    }

    public Receipt setCost(int cost) {
        this.cost = cost;
        return this;
    }
}
