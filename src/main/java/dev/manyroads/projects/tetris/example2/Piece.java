package dev.manyroads.projects.tetris.example2;

public abstract class Piece {

    final int DIMENSION = 4;

    protected void print(int[] piece) {
        int countPiece = 0, countGrid = 0;
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (countPiece < DIMENSION && piece[countPiece] == countGrid++) {
                    System.out.print('0');
                    countPiece++;
                } else {
                    System.out.print('-');
                }
                System.out.print(j < DIMENSION - 1 ? " " : "");
            }
            System.out.println();
        }
    }
    protected void printEmpty(){
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                System.out.print("- ");
            }
            System.out.println();
        }
    }
    public abstract void rotateLeft();
    public abstract void rotateRight();
    public abstract void rotateFull();
}
