package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        // Parse command-line arguments
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("i", "input", true, "Path to the maze file");
        options.addOption("p", "path", true, "Path to validate");

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("i")) {
                String mazePath = cmd.getOptionValue("i");
                logger.info("Starting Maze Runner with maze file: {}", mazePath);
                File mazeFile = new File(mazePath);
                if (!mazeFile.exists()) {
                    logger.error("Maze file not found: {}", mazePath);
                    return;
                }
                Maze maze = new Maze(mazeFile);
                Explorer explorer = new Explorer(maze, 1, 0, Direction.EAST);
                String generatedPath = explorer.explore();
                logger.info("Generated Path: {}", generatedPath);
            }
            if (cmd.hasOption("p")) {
                String path = cmd.getOptionValue("p");
                logger.info("Verifying path: {}", path);
                // Path validation (yet to be implemented)
            }
        } catch (ParseException e) {
            logger.error("Error parsing command-line arguments: {}", e.getMessage());
            printUsage(options);
        } catch (Exception e) {
            logger.error("An unexpected error occurred: ", e);
        }
    }

    private static void printUsage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("MazeRunner", options);
    }
}
