package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        // Parse command-line arguments
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("i", "input", true, "Path to the maze file");
        options.addOption("p", "path", true, "Path to verify");

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
                processMazeFile(mazeFile);
            }
            if (cmd.hasOption("p")) {
                String path = cmd.getOptionValue("p");
                logger.info("Verifying path: {}", path);
                verifyPath(path);
            }
        } catch (ParseException e) {
            logger.error("Error parsing command-line arguments: {}", e.getMessage());
            printUsage(options);
        } catch (Exception e) {
            logger.error("An unexpected error occurred: ", e);
        }
    }

    private static void processMazeFile(File mazeFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(mazeFile))) {
            String line;
            logger.info("Reading maze file...");
            while ((line = reader.readLine()) != null) {
                StringBuilder output = new StringBuilder();
                for (char c : line.toCharArray()) {
                    if (c == '#') {
                        output.append("WALL ");
                    } else if (c == ' ') {
                        output.append("PASS ");
                    }
                }
                logger.info(output.toString());
            }
            logger.info("Maze processing completed.");
        } catch (Exception e) {
            logger.error("Error reading maze file: ", e);
        }
    }

    private static void verifyPath(String path) {
        // Placeholder for path verification logic
        logger.info("Path verification not implemented yet.");
    }

    private static void printUsage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("MazeRunner", options);
    }
}
