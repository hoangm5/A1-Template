package ca.mcmaster.se2aa4.mazerunner.exploration;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.utils.Direction;
import java.util.ArrayList;
import java.util.List;

public abstract class Explorer {
    protected Maze maze;
    protected int row, col;
    protected Direction direction;
    protected List<String> path;

    public Explorer(Maze maze) {
        this.maze = maze;
        this.row = maze.getEntryRow();
        this.col = maze.getEntryCol();
        this.direction = Direction.EAST; // Assuming entry is on the left
        this.path = new ArrayList<>();
    }

    public abstract void startExploration();
}
