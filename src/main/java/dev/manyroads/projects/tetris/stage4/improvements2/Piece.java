package dev.manyroads.projects.tetris.stage4.improvements2;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Piece {
    private String name;
    private Integer[][] states;
    private List <Integer> currentState;
    private int stateIndex;
    private int currentRow;
    private int currentCol;
    private boolean bottom;




    public boolean isBottom(){
        return this.bottom;
    }

    public void setCurrentState(int i, Integer stateMember){
        this.currentState.set(i, stateMember);
    }

    public void setCurrentRow(){
        this.currentRow++;
    }

    public void setStateIndex(int stIn){
        this.stateIndex = stIn;
    }

    public void setCurrentCol(int curCol){
        this.currentCol = curCol;
    }

    public void setBottom(boolean bott){
        this.bottom = bott;
    }

    public Piece(String name, Integer[][] states){
        this.name = name;
        this.states = states;
        this.currentState = Arrays.asList(Arrays.copyOf(states[0], 4));
        this.stateIndex = 0;
        this.currentCol = 0;
        this.currentRow = 0;
        this.bottom = false;

    }

}
