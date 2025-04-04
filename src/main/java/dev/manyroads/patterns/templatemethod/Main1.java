package dev.manyroads.patterns.templatemethod;

import java.util.Scanner;

/**
 * Template Method
 * There are two classes: an abstract class Meal with the template method and a concrete class Steak
 * <p>
 * Every meal is different, but the common algorithm is known.
 * <p>
 * You must implement the template method in Meal class to cook a meal with the following approach:
 * <p>
 * Prepare the ingredients;
 * Cook the meal;
 * Enjoy the meal;
 * Wash the dishes after the meal.
 * <p>
 * Now, we'll add a new concrete class Sandwich.
 * Sandwich is different from Steak, that's why you need to implement it.
 * The first line of the standard input is the concrete meal.
 * You must output the meal procedure.
 * Input
 * Sandwich
 * Sample Output 1:
 * <p>
 * Ingredients: bacon, white bread, egg, cheese, mayonnaise, tomato
 * Paste ingredients between bread slices. Toast sandwich
 * That's good
 * Lick fingers and go to sleep
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
//        System.out.println(name + " wants to eat");
//        System.out.println(name + " decides to cook meal");
//        Meal steak = new Steak();
//        steak.prepareMeal();
        if ("Sandwich".equals(name)) {
            Meal sandwich = new Sandwich();
            sandwich.prepareMeal();
        } else if ("Steak".equals(name)) {
            Meal steak = new Steak();
            steak.prepareMeal();
        } else System.out.println("Error");
    }
}

abstract class Meal {

    public void prepareMeal() {
        prepareIngredients();
        cookTheMeal();
        enjoyTheMeal();
        washDishesAfterMeal();
    }

    abstract void prepareIngredients();

    abstract void cookTheMeal();

    void enjoyTheMeal() {
        System.out.println("That's good");
    }

    abstract void washDishesAfterMeal();
}

class Steak extends Meal {

    @Override
    void prepareIngredients() {
        System.out.println("Ingredients: beef steak, lemon, olive oil, salt, sugar");
    }

    @Override
    void cookTheMeal() {
        System.out.println("Fry the steak in the pan");
    }

    @Override
    void washDishesAfterMeal() {
        System.out.println("Push dishes in the sink and go coding");
    }
}

class Sandwich extends Meal {

    @Override
    void prepareIngredients() {
        System.out.println("Ingredients: bacon, white bread, egg, cheese, mayonnaise, tomato");
    }

    @Override
    void cookTheMeal() {
        System.out.println("Paste ingredients between bread slices. Toast sandwich");
    }

    @Override
    void washDishesAfterMeal() {
        System.out.println("Lick fingers and go to sleep");
    }
}
