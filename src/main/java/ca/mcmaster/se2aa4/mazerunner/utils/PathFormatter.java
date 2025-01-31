package ca.mcmaster.se2aa4.mazerunner.utils;

import java.util.List;

public class PathFormatter {
    public static String factorize(List<String> path) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= path.size(); i++) {
            if (i < path.size() && path.get(i).equals(path.get(i - 1))) {
                count++;
            } else {
                if (count > 1) {
                    result.append(count).append(path.get(i - 1)).append(" ");
                } else {
                    result.append(path.get(i - 1)).append(" ");
                }
                count = 1;
            }
        }

        return result.toString().trim();
    }
}
