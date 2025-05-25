package dev.manyroads.projects.tetris.stage4.improvements2;


public enum FieldStatus {
    TAKEN("0"),
    FREE("-");

    private final String symbol;
    FieldStatus(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol(){
        return symbol;
    }
}
