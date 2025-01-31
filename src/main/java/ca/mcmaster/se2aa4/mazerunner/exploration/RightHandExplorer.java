package ca.mcmaster.se2aa4.mazerunner.exploration;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.utils.Direction;
import ca.mcmaster.se2aa4.mazerunner.utils.PathFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandExplorer extends Explorer {
    private static final Logger logger = LogManager.getLogger(RightHandExplorer.class);

    public RightHandExplorer(Maze maze) {
        super(maze);
    }

    @Override
    public void startExploration() {
        logger.info("Explorer initialized at entry: (" + row + ", " + col + ")");
        
        while (!maze.isExit(row, col)) {
            if (canMoveRight()) {
                turnRight();
                moveForward();
            } else if (canMoveForward()) {
                moveForward();
            } else {
                turnLeft();
            }
            logger.info("Explorer at position: (" + row + ", " + col + ")");
        }

        logger.info("Explorer Exit found at position: (" + row + ", " + col + ")");
    }

    private boolean canMoveRight() {
        Direction rightDir = direction.turnRight();
        return canMove(rightDir);
    }

    private boolean canMoveForward() {
        return canMove(direction);
    }

    private boolean canMove(Direction dir) {
        int newRow = row + dir.getRowOffset();
        int newCol = col + dir.getColOffset();
        return !maze.isWall(newRow, newCol);
    }

    private void moveForward() {
        row += direction.getRowOffset();
        col += direction.getColOffset();
        path.add("F");
    }

    private void turnRight() {
        direction = direction.turnRight();
        path.add("R");
    }

    private void turnLeft() {
        direction = direction.turnLeft();
        path.add("L");
    }

    public String getFactorizedPath() {
        return PathFormatter.factorize(path);
    }
}
