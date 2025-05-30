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
    private List<Integer> currentState;
    private int stateIndex;
    private int currentRow;
    private int currentCol;
    private boolean bottom;

    public Piece(String name, Integer[][] states) {
        this.name = name;
        this.states = states;
        this.currentState = Arrays.asList(Arrays.copyOf(states[0], 4));
        this.stateIndex = 0;
        this.currentCol = 0;
        this.currentRow = 0;
        this.bottom = false;

    }

    public void setCurrentState(int i, Integer stateMember) {
        this.currentState.set(i, stateMember);
    }

    public void setCurrentRow() {
        this.currentRow++;
    }


}
