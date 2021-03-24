package com.codecool.chessopen;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName){
        List<String> playerStatistics = readFileByLines(fileName);
        Map<String, Integer> statisticMap = parseLinesToMap(playerStatistics);
        List<String> result = statisticMap.entrySet()
                                          .stream()
                                          .sorted(Map.Entry.comparingByValue())
                                          .map(i-> i.getKey())
                                          .collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }

    public static List<String> readFileByLines(String fileName) {
        List<String> lines = new ArrayList<>();
        Path file = Path.of(fileName);
        try (BufferedReader reader = Files.newBufferedReader(file)){
            lines = reader.lines().collect(Collectors.toList());
        } catch (IOException io) {
            System.out.println("File not found!");
        }
        return lines;
    }

    public static Map<String, Integer> parseLinesToMap(List<String> lines) {
        Map<String, Integer> map = new HashMap<>();
        for (String line : lines) {
            String[] elements = line.split(",");
            Integer sum = Integer.parseInt(elements[1]) +
                          Integer.parseInt(elements[2]) +
                          Integer.parseInt(elements[3]) +
                          Integer.parseInt(elements[4]) +
                          Integer.parseInt(elements[5]);
            map.put(elements[0], sum);
        }
        return map;
    }
}
