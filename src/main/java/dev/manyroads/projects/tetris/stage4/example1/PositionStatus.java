package dev.manyroads.projects.tetris.stage4.example1;


public enum PositionStatus {
    TAKEN("0"), FREE("-");
    private final String sign;
    PositionStatus(String sign){
        this.sign = sign;
    }
    public String getSign(){
        return sign;
    }
}
