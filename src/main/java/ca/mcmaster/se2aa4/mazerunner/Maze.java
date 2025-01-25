package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;

public class Maze {
    private char[][] grid;
    private int rows, cols;

    public Maze(File mazeFile) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(mazeFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        rows = lines.size();
        cols = lines.get(0).length();
        grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }
    }

    public boolean isWall(int row, int col) {
        return grid[row][col] == '#';
    }

    public boolean isPassage(int row, int col) {
        return grid[row][col] == ' ';
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
