package ca.mcmaster.se2aa4.mazerunner.utils;

public enum Direction {
    NORTH(-1, 0), EAST(0, 1), SOUTH(1, 0), WEST(0, -1);

    private final int rowOffset, colOffset;

    Direction(int rowOffset, int colOffset) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }

    public int getRowOffset() { return rowOffset; }
    public int getColOffset() { return colOffset; }

    public Direction turnRight() {
        return values()[(ordinal() + 1) % 4];
    }

    public Direction turnLeft() {
        return values()[(ordinal() + 3) % 4];
    }
}
