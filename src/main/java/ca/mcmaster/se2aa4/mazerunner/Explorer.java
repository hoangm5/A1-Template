package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Explorer {
    private static final Logger logger = LogManager.getLogger(Explorer.class); // Initialize the logger
    private int row, col;
    private Direction direction;
    private Maze maze;
    private StringBuilder path;
    private int steps; // To track number of steps and prevent infinite loops
    private Set<String> visitedPositions = new HashSet<>(); // To track visited positions

    public Explorer(Maze maze, int startRow, int startCol, Direction startDirection) {
        this.maze = maze;
        this.row = startRow;
        this.col = startCol;
        this.direction = startDirection;
        this.path = new StringBuilder();
        this.steps = 0; // Initialize steps
    }

    public String explore() {
        while (!isAtExit() && steps < maze.getRows() * maze.getCols()) { // Prevent infinite loops by tracking steps
            if (hasVisitedPosition()) {
                logger.debug("Revisiting position: ({}, {})", row, col);
            }

            logger.info("Exploring at position: ({}, {}) facing {}", row, col, direction);

            if (canTurnRight()) {
                turnRight();
                moveForward();
                path.append("R F ");
            } else if (canMoveForward()) {
                moveForward();
                path.append("F ");
            } else {
                turnLeft();
                path.append("L ");
            }

            steps++; // Increment step count
            logger.info("Path: {}", path.toString()); // Log the path after each step
        }

        if (steps >= maze.getRows() * maze.getCols()) {
            logger.error("Explorer reached step limit without finding exit.");
            return "Path search terminated due to step limit.";
        }

        return path.toString().trim();
    }

    private boolean isAtExit() {
        // Ensure the explorer has moved past the entry point before checking for the exit
        boolean atExit = (row != 1 || col != 0) && (col == maze.getCols() - 1 || col == 0); // Exit at East or West edge
        if (atExit) {
            logger.info("Exit found at position: ({}, {})", row, col);
        }
        return atExit;
    }

    private boolean hasVisitedPosition() {
        String position = row + "," + col;
        if (visitedPositions.contains(position)) {
            logger.debug("Already visited position: ({}, {})", row, col);
            return true;
        } else {
            visitedPositions.add(position);
            return false;
        }
    }

    private boolean canMoveForward() {
        // Check if the next cell in the current direction is a valid passage
        switch (direction) {
            case NORTH:
                return row > 0 && maze.isPassage(row - 1, col);
            case EAST:
                return col < maze.getCols() - 1 && maze.isPassage(row, col + 1);
            case SOUTH:
                return row < maze.getRows() - 1 && maze.isPassage(row + 1, col);
            case WEST:
                return col > 0 && maze.isPassage(row, col - 1);
            default:
                return false;
        }
    }

    private boolean canTurnRight() {
        // Check if we can turn right
        Direction rightDirection = direction.turnRight();
        switch (rightDirection) {
            case NORTH:
                return row > 0 && maze.isPassage(row - 1, col);
            case EAST:
                return col < maze.getCols() - 1 && maze.isPassage(row, col + 1);
            case SOUTH:
                return row < maze.getRows() - 1 && maze.isPassage(row + 1, col);
            case WEST:
                return col > 0 && maze.isPassage(row, col - 1);
            default:
                return false;
        }
    }

    private void moveForward() {
        // Move forward in the current direction
        switch (direction) {
            case NORTH:
                row--;
                break;
            case EAST:
                col++;
                break;
            case SOUTH:
                row++;
                break;
            case WEST:
                col--;
                break;
        }
    }

    private void turnRight() {
        // Turn right (change direction)
        direction = direction.turnRight();
    }

    private void turnLeft() {
        // Turn left (change direction)
        direction = direction.turnLeft();
    }
}
