package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.exploration.RightHandExplorer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length != 1) {
            logger.error("Usage: java -jar mazerunner.jar <maze_file>");
            System.exit(1);
        }

        try {
            Maze maze = new Maze(args[0]);
            logger.info("Maze initialized.\n" + maze);
            
            RightHandExplorer explorer = new RightHandExplorer(maze);
            explorer.startExploration();
            
            logger.info("Factorized Path: " + explorer.getFactorizedPath());

        } catch (IOException e) {
            logger.error("Error loading maze: " + e.getMessage());
        }
    }
}
