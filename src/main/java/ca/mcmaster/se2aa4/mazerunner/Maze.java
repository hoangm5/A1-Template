package ca.mcmaster.se2aa4.mazerunner.maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Maze {
    private final char[][] grid;
    private final int rows, cols;
    private int entryRow, entryCol, exitRow, exitCol;

    public Maze(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        rows = lines.size();
        cols = lines.get(0).length();
        grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        findEntryExit();
    }

    private void findEntryExit() {
        for (int r = 0; r < rows; r++) {
            if (grid[r][0] == ' ') { entryRow = r; entryCol = 0; }
            if (grid[r][cols - 1] == ' ') { exitRow = r; exitCol = cols - 1; }
        }
    }

    public boolean isWall(int r, int c) {
        return grid[r][c] == '#';
    }

    public boolean isExit(int r, int c) {
        return r == exitRow && c == exitCol;
    }

    public int getEntryRow() { return entryRow; }
    public int getEntryCol() { return entryCol; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append(row).append("\n");
        }
        return sb.toString();
    }
}
