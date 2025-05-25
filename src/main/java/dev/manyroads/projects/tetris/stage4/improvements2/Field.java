package dev.manyroads.projects.tetris.stage4.improvements2;


public class Field {
    private FieldStatus fieldStatus;
    private int x;
    private int y;

    public Field(int x, int y, FieldStatus fieldStatus){
        this.x = x;
        this.y = y;
        this.fieldStatus = fieldStatus;
    }

    public FieldStatus getFieldStatus(){
        return this.fieldStatus;
    }

    public void setFieldStatus(FieldStatus fieldStatus){
        this.fieldStatus = fieldStatus;
    }
}
