package dev.manyroads.projects.trafficlightsimulator.stage2.example3;


public enum Message {
    MENU_TEXT("""
            Menu:
            1. Add road
            2. Delete road
            3. Open system
            0. Quit"""),
    WELCOME_TEXT("Welcome to the traffic management system!"),
    NUMBER_OF_ROADS("Input the number of roads: "),
    NUMBER_OF_INTERVALS("Input the interval: "),
    ROAD_ADDED("Road added"),
    ROAD_DELETED("Road deleted"),
    SYSTEM_OPENED("System opened"),
    BYE("Bye!"),
    ENTER_VALID_OPTION("Enter a valid option");

    private final String text;

    Message(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
}
