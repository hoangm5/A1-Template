public class Explorer {
    private int row, col;
    private Direction direction;
    private Maze maze;

    public Explorer(Maze maze, int startRow, int startCol, Direction startDirection) {
        this.maze = maze;
        this.row = startRow;
        this.col = startCol;
        this.direction = startDirection;
    }

    public void moveForward() {
        switch (direction) {
            case NORTH -> row--;
            case EAST -> col++;
            case SOUTH -> row++;
            case WEST -> col--;
        }
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public boolean canMoveForward() {
        int nextRow = row, nextCol = col;
        switch (direction) {
            case NORTH -> nextRow--;
            case EAST -> nextCol++;
            case SOUTH -> nextRow++;
            case WEST -> nextCol--;
        }
        return !maze.isWall(nextRow, nextCol);
    }

    public String explore() {
        StringBuilder path = new StringBuilder();
        while (!isAtExit()) {
            if (canMoveForward()) {
                moveForward();
                path.append("F ");
            } else {
                turnRight();
                path.append("R ");
            }
        }
        return path.toString().trim();
    }

    private boolean isAtExit() {
        return row == 0 || row == maze.getRows() - 1 || col == 0 || col == maze.getCols() - 1;
    }
}

